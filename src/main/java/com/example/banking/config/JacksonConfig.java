package com.example.banking.config;

import com.example.banking.models.Account;
import com.example.banking.models.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder,
                                     CustomerDeserializer customerDeserializer,
                                     AccountDeserializer accountDeserializer) {
        // Create a module to register the custom deserializers
        SimpleModule customModule = new SimpleModule();
        customModule.addDeserializer(Customer.class, customerDeserializer);
        customModule.addDeserializer(Account.class, accountDeserializer);

        // Use the builder to create and configure the ObjectMapper with the custom module
        return builder.modules(customModule).build();
    }
}
