package com.example.banking.service;

import com.example.banking.config.EmailConfig;
import com.example.banking.models.Customer;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {
    private static EmailConfig emailConfig;

    @Autowired
    public EmailService(EmailConfig emailConfig) {
        EmailService.emailConfig = emailConfig;
    }

    public static void sendVerificationEmail(Customer customer, JavaMailSender mailSender)
            throws MessagingException, UnsupportedEncodingException {
        String fromAddress = emailConfig.getFromAddress();
        String senderName = emailConfig.getSenderName();
        String subject = emailConfig.getSubject();
        String content = emailConfig.getContent();
        String toAddress = customer.getEmail();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", customer.getName());
        content = content.replace("[[customerId]]", customer.getCustomerId()+"");
        helper.setText(content, true);

        System.out.println();

        mailSender.send(message);

    }
}
