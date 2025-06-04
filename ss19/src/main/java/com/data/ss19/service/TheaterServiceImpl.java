package com.data.ss19.service;


import com.data.ss19.model.Theater;
import com.data.ss19.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TheaterServiceImpl implements TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }

    @Override
    public Theater findById(Long id) {
        return theaterRepository.findById(id);
    }

    @Override
    public void save(Theater theater) {
        theaterRepository.save(theater);
    }

    @Override
    public void update(Theater theater) {
        theaterRepository.update(theater);
    }

    @Override
    public void delete(Long id) {
        theaterRepository.delete(id);
    }

    @Override
    public List<Theater> findByStatus(Boolean status) {
        return theaterRepository.findByStatus(status);
    }
}