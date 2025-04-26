package com.archexc.acctmgmtservice.model;

public record NotificationEvent(String userEmail, String type, String message) {}