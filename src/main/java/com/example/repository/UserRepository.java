package com.example.repository;

import com.example.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
}
