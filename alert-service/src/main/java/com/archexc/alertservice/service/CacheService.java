package com.archexc.alertservice.service;

import com.archexc.alertservice.model.UserDetail;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    public void clearCache() {
        // Logic to clear the cache
        System.out.println("Cache cleared");
    }


    public UserDetail getUserDetails(Long bankAccountId) {
        return UserDetail.builder()
                .name("John Doe")
                .email("john.doe@gmail.com")
                .build();
    }
}
