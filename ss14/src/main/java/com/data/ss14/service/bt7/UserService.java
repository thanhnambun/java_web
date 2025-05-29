package com.data.ss14.service.bt7;

import com.data.ss14.model.B7.User;

public interface UserService {
    User login(String username, String password);
}