package com.ComeOnBaby.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendEmail(String recipientMail , String recipientName) {

        MimeMessagePreparator preparator = getMessagePreparator(recipientMail , recipientName);

        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(final String recipientMail , final String recipientName) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom("SoftwareFactory@server.com");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(recipientMail));
                mimeMessage.setText("Dear " + recipientName
                        + "hello");
                mimeMessage.setSubject("helloMessage");
            }
        };
        return preparator;
    }

}