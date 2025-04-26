package com.archexc.alertservice.model;

public record NotificationEvent(String userEmail, String type, String message) {}