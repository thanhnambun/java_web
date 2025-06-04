package com.data.ss19.repository;


import com.data.ss19.model.Movie;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Movie> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Movie ORDER BY id DESC", Movie.class)
                .getResultList();
    }

    public Movie findById(Long id) {
        return sessionFactory.getCurrentSession().get(Movie.class, id);
    }

    public void save(Movie movie) {
        sessionFactory.getCurrentSession().save(movie);
    }

    public void update(Movie movie) {
        sessionFactory.getCurrentSession().update(movie);
    }

    public void delete(Long id) {
        Movie movie = findById(id);
        if (movie != null) {
            movie.setStatus(false);
            update(movie);
        }
    }

    public List<Movie> findByStatus(Boolean status) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Movie WHERE status = :status ORDER BY id DESC", Movie.class)
                .setParameter("status", status)
                .getResultList();
    }
}