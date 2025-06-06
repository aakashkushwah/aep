package com.archexc.notificationservice.service;

import com.archexc.notificationservice.dto.NotificationEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;
import java.util.Map;

@Service
public class ReactiveKafkaConsumer {
    private final MailService mailService;
    private final String kafkaBootstrapServers;

    public ReactiveKafkaConsumer(MailService mailService, @Value("${kafka.bootstrap-servers}") String kafkaBootstrapServers) {
        this.mailService = mailService;
        this.kafkaBootstrapServers = kafkaBootstrapServers;
        consumeMessages();
    }

    private void consumeMessages() {
        ReceiverOptions<String, NotificationEvent> options = ReceiverOptions.<String, NotificationEvent>create(
                Map.of(
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers,
                        ConsumerConfig.GROUP_ID_CONFIG, "test-group",
                        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                        JsonDeserializer.TRUSTED_PACKAGES, "*"
                )
        ).withValueDeserializer(new JsonDeserializer<>(NotificationEvent.class, false));

        KafkaReceiver.create(options.subscription(List.of("notification-topic")))
                .receive()
                .doOnNext(record -> {
                    System.out.println("Received message: " + record);
                    NotificationEvent event = record.value();
                    mailService.sendEmail(event).subscribe();
                })
                .subscribe();
    }
}
