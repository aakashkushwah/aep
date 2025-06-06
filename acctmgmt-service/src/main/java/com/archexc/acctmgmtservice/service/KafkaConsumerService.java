package com.archexc.acctmgmtservice.service;

import com.archexc.acctmgmtservice.model.AccountEvent;
import com.archexc.acctmgmtservice.model.ReceiverStatusChangeRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;
import java.util.Map;

@Service
public class KafkaConsumerService {

    private final ReceiverService receiverService;
    private final String kafkaBootstrapServers;

    public KafkaConsumerService(ReceiverService receiverService, @Value("${kafka.bootstrap-servers}") String kafkaBootstrapServers) {
        this.receiverService = receiverService;
        this.kafkaBootstrapServers = kafkaBootstrapServers;
        consumeMessages();
    }

//    @KafkaListener(topics = "test-topic", groupId = "test-group")
//    public void consume(String message) {
//        System.out.println("Consumed: " + message);
//    }
//
//    @KafkaListener(topics = "account-topic", groupId = "test-group")
//    public void consumeAccountEvent(AccountEvent accountEvent) {
//        System.out.println("Consumed: " + accountEvent);
//        if(accountEvent!= null && accountEvent.getStatus().equalsIgnoreCase("Banned")) {
//            System.out.println("Account is banned");
//            ReceiverStatusChangeRequest receiverStatusChangeRequest = new ReceiverStatusChangeRequest();
//            receiverStatusChangeRequest.setReceiverAccountNumber(accountEvent.getReceiverAccountNumber());
//            receiverStatusChangeRequest.setStatus("Banned");
//            receiverService.banAccount(receiverStatusChangeRequest);
//        }
//
//    }

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

        KafkaReceiver.create(options.subscription(List.of("account-topic")))
                .receive()
                .doOnNext(record -> {
                    System.out.println("Received message: " + record);
                    AccountEvent accountEvent = record.value();
                    if(accountEvent!= null && accountEvent.getStatus().equalsIgnoreCase("Banned")) {
                        System.out.println("Account is banned");
                        ReceiverStatusChangeRequest receiverStatusChangeRequest = new ReceiverStatusChangeRequest();
                        receiverStatusChangeRequest.setReceiverAccountNumber(accountEvent.getReceiverAccountNumber());
                        receiverStatusChangeRequest.setStatus("Banned");
                        receiverService.banAccount(receiverStatusChangeRequest);
                    }
                })
                .subscribe();
    }
}
