package com.example.banking.config;

import com.example.banking.models.Account;
import com.example.banking.models.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(CustomerDeserializer customerDeserializer, AccountDeserializer accountDeserializer) {
        ObjectMapper mapper = new ObjectMapper();

        // Configure the module to handle custom deserializers
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Customer.class, customerDeserializer);
        module.addDeserializer(Account.class, accountDeserializer);

        // Register the module with the object mapper
        mapper.registerModule(module);

        return mapper;
    }


}
