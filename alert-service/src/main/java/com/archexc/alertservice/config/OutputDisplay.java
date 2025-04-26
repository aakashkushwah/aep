package com.archexc.alertservice.config;

import java.time.LocalDate;

public class OutputDisplay {

    public void showText(String text) {
        System.out.println("==================================================");
        System.out.println("Text: " + text + ",  date: " + LocalDate.now());
        System.out.println("==================================================");
    }
}
