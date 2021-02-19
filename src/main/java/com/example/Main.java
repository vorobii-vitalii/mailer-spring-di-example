package com.example;

import com.example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("spring-config.xml");

        var userService = context.getBean("userService", UserService.class);

        userService.messageAllUsers("Example", "Some example message");
    }
}
