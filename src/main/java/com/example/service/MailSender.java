package com.example.service;

public interface MailSender {
    void sendMessage(String subject, String messageContent, String receiverEmail);
}
