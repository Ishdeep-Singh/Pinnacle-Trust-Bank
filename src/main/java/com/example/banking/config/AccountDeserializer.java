package com.example.banking.config;

import com.example.banking.models.Account;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.DeserializationContext;

import java.io.IOException;

@Component
public class AccountDeserializer extends JsonDeserializer<Account> {

    @Override
    public Account deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        // Check if the "type" field exists
        JsonNode typeNode = node.get("type");
        if (typeNode == null || typeNode.isNull()) {
            throw new IllegalArgumentException("Missing or null 'type' field");
        }
        String type = typeNode.asText();


//        if ("SavingsAccount".equalsIgnoreCase(type)) {
//            return jsonParser.getCodec().treeToValue(node, SavingsAccount.class);
//        } else if ("CheckingAccount".equalsIgnoreCase(type)) {
//            return jsonParser.getCodec().treeToValue(node, CheckingAccount.class);
//        }
//
//        throw new IllegalArgumentException("Unknown type: " + type);
        return jsonParser.getCodec().treeToValue(node, Account.class);
    }
}

