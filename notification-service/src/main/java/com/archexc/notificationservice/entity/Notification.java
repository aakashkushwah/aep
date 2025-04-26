package com.archexc.notificationservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Data
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "user_email", nullable = false, columnDefinition = "TEXT")
    private String userEmail;

    @Column(name = "subject", nullable = false, columnDefinition = "TEXT")
    private String subject;

    @Column(name = "email_content", nullable = false, columnDefinition = "TEXT")
    private String emailContent;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
