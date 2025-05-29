package com.data.ss14.dao.B6;

import com.data.ss14.model.B6.User;

import java.util.List;

public interface UserDAO {
    void save(User user);
    List<User> getAllUsers();
}