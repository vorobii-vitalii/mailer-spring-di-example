package com.example.service.impl;

import com.example.service.MailSender;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MailSender mailSender;

    public UserServiceImpl(UserRepository userRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    @Override
    public void messageAllUsers(String subject, String messageContent) {
        var users = userRepository.getAllUsers();

        for (User user : users) {
            var receiverEmail = user.getEmail();

            mailSender.sendMessage(subject, messageContent, receiverEmail);
        }
    }

}
