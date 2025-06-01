package com.data.ss16.service;


import com.data.ss16.model.User;


import com.data.ss16.responsitory.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean register(User user, StringBuilder errorMsg) {
        if (userDAO.existsByUsername(user.getUsername())) {
            errorMsg.append("Tên đăng nhập đã tồn tại!");
            return false;
        }
        if (userDAO.existsByEmail(user.getEmail())) {
            errorMsg.append("Email đã tồn tại!");
            return false;
        }
        user.setRole("USER");
        user.setStatus(1);
        userDAO.insertUser(user);
        return true;
    }

    @Override
    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password) && user.getStatus() == 1) {
            return user;
        }
        return null;
    }
}