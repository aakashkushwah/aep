package com.archexc.alertservice.config;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "com.archexc.alertservice.service")
public class DroolsConfig {

    private static final KieServices kieServices = KieServices.Factory.get();
    private static final String RULES_CUSTOMER_RULES_DRL = "rules/rules.drl";

//    @Bean
//    public KieContainer kieContainer() {
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_CUSTOMER_RULES_DRL));
//        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
//        kb.buildAll();
//        KieModule kieModule = kb.getKieModule();
//        return kieServices.newKieContainer(kieModule.getReleaseId());
//    }

    @Bean
    public KieContainer kieContainer() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_CUSTOMER_RULES_DRL));
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

//    @Bean
//    public KieSession kieSession(KieContainer kieContainer) {
//        KieSession kieSession = kieContainer.newKieSession();
//        RuleRuntimeEventListener listener = new RuleRuntimeEventListener() {
//            private final Logger logger = LoggerFactory.getLogger(RuleRuntimeEventListener.class);
//
//            @Override
//            public void objectInserted(ObjectInsertedEvent event) {
//                logger.info("Object inserted: {}", event.getObject());
//            }
//
//            @Override
//            public void objectUpdated(ObjectUpdatedEvent event) {
//                logger.info("Object updated: {}", event.getObject());
//            }
//
//            @Override
//            public void objectDeleted(ObjectDeletedEvent event) {
//                logger.info("Object deleted: {}", event.getOldObject());
//            }
//        };
//        kieSession.addEventListener(listener);
//        return kieSession;
//    }

}