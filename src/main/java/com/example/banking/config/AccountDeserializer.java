package com.example.banking.config;

import com.example.banking.models.Account;
import com.example.banking.models.Current;
import com.example.banking.models.Saving;
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
        JsonNode typeNode = node.get("accountType");
        if (typeNode == null || typeNode.isNull()) {
            throw new IllegalArgumentException("Missing or null 'accountType' field");
        }
        String type = typeNode.asText();


        if ("Saving".equalsIgnoreCase(type)) {
            return jsonParser.getCodec().treeToValue(node, Saving.class);
        } else if ("Current".equalsIgnoreCase(type)) {
            return jsonParser.getCodec().treeToValue(node, Current.class);
        }else{
            new IllegalArgumentException("Unknown type: " + type);
        }
        return jsonParser.getCodec().treeToValue(node, Account.class);
    }
}

