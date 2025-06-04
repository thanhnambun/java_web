package com.data.ss19.service;


import com.data.ss19.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(Long id);
    void save(Movie movie);
    void update(Movie movie);
    void delete(Long id);
    List<Movie> findByStatus(Boolean status);
}