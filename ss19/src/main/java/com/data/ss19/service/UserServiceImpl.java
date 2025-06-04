package com.data.ss19.service;


import com.data.ss19.model.User;
import com.data.ss19.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllWithPagination(int page, int size, String search) {
        return userRepository.findAllWithPagination(page, size, search);
    }

    @Override
    public long countWithSearch(String search) {
        return userRepository.countWithSearch(search);
    }

    @Override
    public long countAll() {
        return userRepository.countAll();
    }
}