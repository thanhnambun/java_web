package com.data.ss19.service;


import com.data.ss19.model.User;

import java.util.List;

public interface UserService {
    void update(User customer);
    User findById(Integer id);

    List<User> findAllWithPagination(int page, int size, String search);
    long countWithSearch(String search);
    long countAll();
}