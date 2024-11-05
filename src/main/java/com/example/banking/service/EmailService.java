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

    public static String RequiredString(int size)
    {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        // create StringBuffer size of AlphaNumericString
        StringBuilder s = new StringBuilder(size);
        int y;
        for ( y = 0; y < size; y++) {
            // generating a random number
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            // add Character one by one in end of s
            s.append(AlphaNumericString
                    .charAt(index));
        }
        return s.toString();
    }

    public static void sendVerificationEmail(Customer customer, JavaMailSender mailSender, String siteURL)
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


        String verifyURL = siteURL + "/verify_customer?code=" + customer.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        System.out.println();

        mailSender.send(message);

    }
}
