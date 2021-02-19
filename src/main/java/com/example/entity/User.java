package com.example.entity;

public class User {
    private final String userName;
    private final String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

}
