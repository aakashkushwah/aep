package com.archexc.notificationservice.service;

import com.archexc.notificationservice.dto.NotificationEvent;
import com.archexc.notificationservice.entity.Notification;
import com.archexc.notificationservice.repository.NotificationRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@Service
public class MailService {

    private NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender, NotificationRepository notificationRepository) {
        this.mailSender = mailSender;
        this.notificationRepository = notificationRepository;
    }

    public Mono<Void> sendEmail(NotificationEvent event) {
        return Mono.fromRunnable(() -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(event.userEmail());
            message.setSubject("[" + event.type() + "] Notification");
            message.setText(event.message());
            mailSender.send(message);
            notificationRepository.save(Notification.builder()
                            .emailContent(event.message())
                            .createdAt(LocalDateTime.now())
                            .subject(message.getSubject())
                            .userEmail(event.userEmail())
                    .build());
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }
}

