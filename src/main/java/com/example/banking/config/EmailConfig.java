package com.example.banking.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConfig {

    @Value("${spring.mail.username}")
    private String fromAddress;

    @Value("${sender_name}")
    private String senderName;

    @Value("${welcome_email_subject}")
    private String subject;

    @Value("${welcome_email_content}")
    private String content;

    public String getFromAddress() {
        return fromAddress;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}

