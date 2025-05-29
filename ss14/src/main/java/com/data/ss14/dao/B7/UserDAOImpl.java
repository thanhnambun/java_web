package com.data.ss14.dao.B7;
import com.data.ss14.model.B7.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("admin", "123"));
        users.add(new User("user1", "abc"));
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}