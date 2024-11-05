package com.example.banking.config;

import com.example.banking.config.AccountDeserializer;
import com.example.banking.config.CustomerDeserializer;
import com.example.banking.models.Account;
import com.example.banking.models.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class JacksonConfig {

    private static final Logger logger = LoggerFactory.getLogger(JacksonConfig.class);

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder,
                                     CustomerDeserializer customerDeserializer,
                                     AccountDeserializer accountDeserializer) {
        // Register JavaTimeModule and custom deserializers
        SimpleModule customModule = new SimpleModule();
        customModule.addDeserializer(Customer.class, customerDeserializer);
        customModule.addDeserializer(Account.class, accountDeserializer);

        // Explicitly add JavaTimeModule support for Java 8 date/time classes
        ObjectMapper objectMapper = builder.build();
        objectMapper.registerModule(new JavaTimeModule());  // Add JavaTimeModule here
        objectMapper.registerModule(customModule);          // Add custom deserializer module

        // Log registered modules
        logger.info("Modules registered: {}", objectMapper.getRegisteredModuleIds());


        return objectMapper;
    }
}
