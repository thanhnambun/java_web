package com.data.ss16.service;


import com.data.ss16.model.User;

public interface UserService {
    boolean register(User user, StringBuilder errorMsg);
    User login(String username, String password);
}