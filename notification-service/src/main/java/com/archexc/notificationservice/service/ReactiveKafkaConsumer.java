package com.archexc.notificationservice.service;

import com.archexc.notificationservice.dto.NotificationEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;
import java.util.Map;

public class ReactiveKafkaConsumer {
    private final MailService mailService;

    public ReactiveKafkaConsumer(MailService mailService) {
        this.mailService = mailService;
        consumeMessages();
    }

    private void consumeMessages() {
        ReceiverOptions<String, NotificationEvent> options = ReceiverOptions.<String, NotificationEvent>create(
                Map.of(
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                        ConsumerConfig.GROUP_ID_CONFIG, "test-group",
                        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                        JsonDeserializer.TRUSTED_PACKAGES, "*"
                )
        ).withValueDeserializer(new JsonDeserializer<>(NotificationEvent.class, false));

        KafkaReceiver.create(options.subscription(List.of("test-topic")))
                .receive()
                .doOnNext(record -> {
                    NotificationEvent event = record.value();
                    mailService.sendEmail(event).subscribe();
                })
                .subscribe();
    }
}
