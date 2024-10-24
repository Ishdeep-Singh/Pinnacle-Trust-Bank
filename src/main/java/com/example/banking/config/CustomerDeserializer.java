package com.example.banking.config;
import com.example.banking.models.Corporate;
import com.example.banking.models.Customer;
import com.example.banking.models.Individual;
import com.example.banking.models.SoleProprietorship;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomerDeserializer extends JsonDeserializer<Customer> {

    @Override
    public Customer deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);


        // Check if the "type" field exists
        JsonNode typeNode = node.get("customerType");
        if (typeNode == null || typeNode.isNull()) {
            throw new IllegalArgumentException("Missing or null 'type' field");
        }
        String type = typeNode.asText();

        if ("Individual".equalsIgnoreCase(type)) {
            return jsonParser.getCodec().treeToValue(node, Individual.class);
        } else if ("Corporate".equalsIgnoreCase(type)) {
            return jsonParser.getCodec().treeToValue(node, Corporate.class);
        } else if ("Sole proprietorship".equalsIgnoreCase(type)) {
            return jsonParser.getCodec().treeToValue(node, SoleProprietorship.class);
        }

        throw new IllegalArgumentException("Unknown Customer Type: " + type);
    }
}
