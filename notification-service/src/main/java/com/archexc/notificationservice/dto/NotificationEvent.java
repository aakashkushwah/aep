package com.archexc.notificationservice.dto;

public record NotificationEvent(String userEmail, String type, String message) {}