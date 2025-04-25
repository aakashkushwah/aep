package com.archexc.notificationservice.service;

import com.archexc.notificationservice.dto.NotificationEvent;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public Mono<Void> sendEmail(NotificationEvent event) {
        return Mono.fromRunnable(() -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(event.userEmail());
            message.setSubject("[" + event.type() + "] Notification");
            message.setText(event.message());
            mailSender.send(message);
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }
}

