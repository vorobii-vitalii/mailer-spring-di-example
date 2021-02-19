package com.example.service.impl;

import com.example.service.MailSender;
import com.example.exception.MessageSendFailureException;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSenderImpl implements MailSender {
    private final Properties mailProps;
    private final String user;
    private final String password;

    public MailSenderImpl(
            String host,
            String user,
            String password,
            boolean authEnabled,
            int port
    ) {
        this.user = user;
        this.password = password;

        mailProps = new Properties();

        mailProps.put("mail.smtp.host", host);
        mailProps.put("mail.smtp.auth", authEnabled ? "true" : "false");
        mailProps.put("mail.smtp.port", Integer.toString(port));
    }


    @Override
    public void sendMessage(String subject, String messageContent, String receiverEmail) {
        var session = getSession();

        try {
            var message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(receiverEmail)
            );
            message.setSubject(subject);
            message.setText(messageContent);

            Transport.send(message);
        }
        catch (MessagingException e) {
            e.printStackTrace();
            throw new MessageSendFailureException();
        }
    }

    private Session getSession() {
        return Session.getDefaultInstance(mailProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
    }

}
