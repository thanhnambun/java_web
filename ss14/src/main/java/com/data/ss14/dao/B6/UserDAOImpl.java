package com.data.ss14.dao.B6;

import com.data.ss14.model.B6.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}