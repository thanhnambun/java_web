package com.data.ss19.service;

import com.data.ss19.model.Movie;
import com.data.ss19.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void update(Movie movie) {
        movieRepository.update(movie);
    }

    @Override
    public void delete(Long id) {
        movieRepository.delete(id);
    }

    @Override
    public List<Movie> findByStatus(Boolean status) {
        return movieRepository.findByStatus(status);
    }
}