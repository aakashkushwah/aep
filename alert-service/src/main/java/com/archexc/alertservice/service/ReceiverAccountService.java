package com.archexc.alertservice.service;

import com.archexc.alertservice.model.AccountEvent;
import com.archexc.alertservice.model.NotificationEvent;
import com.archexc.alertservice.model.ReceiverAccount;
import com.archexc.alertservice.model.UserDetail;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;
import java.util.Map;

@Service
public class ReceiverAccountService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private CacheService cacheService;

    private final String kafkaBootstrapServers;

    public ReceiverAccountService(@Value("${kafka.bootstrap-servers}") String kafkaBootstrapServers) {
        this.kafkaBootstrapServers = kafkaBootstrapServers;
        consumeMessages();
    }


    public void processAccount(ReceiverAccount account, Long bankAccountId) {
        // Example of using KieSession to fire rules
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(account);
        kieSession.fireAllRules();
        // Process the account as needed
        if(account.getStatus().equalsIgnoreCase("Banned")) {
            // Send alert to the bank account
            System.out.println("Account is banned: " + account.getReceiverAccountNumber());
            // Here you can add logic to send an alert to the bank account

            AccountEvent accountEvent = new AccountEvent();
            accountEvent.setReceiverAccountNumber(account.getReceiverAccountNumber());
            accountEvent.setReceiverName(account.getReceiverName());
            accountEvent.setReceiverBankName(account.getReceiverBankName());
            accountEvent.setReceiverCurrency(account.getReceiverCurrency());
            accountEvent.setBankAccountId(bankAccountId);
            accountEvent.setStatus("Banned");
            kafkaProducerService.sendAccountMessage( accountEvent);

            // Send notification to the user
            UserDetail userDetail = cacheService.getUserDetails(bankAccountId);
            NotificationEvent notificationEvent = new NotificationEvent(userDetail.getEmail(), "ALERT",
                    "Your Receiver account "+account.getReceiverAccountNumber()+" is banned and will be deactivated.");
            kafkaProducerService.sendNotificationMessage(notificationEvent);

        } else {
            System.out.println("Account is active: " + account.getReceiverAccountNumber());
        }
    }



    private void consumeMessages() {
        ReceiverOptions<String, AccountEvent> options = ReceiverOptions.<String, AccountEvent>create(
                Map.of(
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers,
                        ConsumerConfig.GROUP_ID_CONFIG, "test-group",
                        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                        JsonDeserializer.TRUSTED_PACKAGES, "*"
                )
        ).withValueDeserializer(new JsonDeserializer<>(AccountEvent.class, false));

        KafkaReceiver.create(options.subscription(List.of("alert-topic")))
                .receive()
                .doOnNext(record -> {
                    System.out.println("Received message: " + record);
                    AccountEvent event = record.value();
                    ReceiverAccount account = ReceiverAccount.builder()
                            .receiverAccountNumber(event.getReceiverAccountNumber())
                            .receiverName(event.getReceiverName())
                            .receiverBankName(event.getReceiverBankName())
                            .receiverCurrency(event.getReceiverCurrency())
                            .build();
                    processAccount(account, event.getBankAccountId());
                })
                .subscribe();
    }

}
