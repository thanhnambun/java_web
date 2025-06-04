package com.data.ss19.repository;


import com.data.ss19.model.Theater;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TheaterRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Theater> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Theater ORDER BY id DESC", Theater.class)
                .getResultList();
    }

    public Theater findById(Long id) {
        return sessionFactory.getCurrentSession().get(Theater.class, id);
    }

    public void save(Theater theater) {
        sessionFactory.getCurrentSession().save(theater);
    }

    public void update(Theater theater) {
        sessionFactory.getCurrentSession().update(theater);
    }

    public void delete(Long id) {
        Theater theater = findById(id);
        if (theater != null) {
            theater.setStatus(false);
            update(theater);
        }
    }

    public List<Theater> findByStatus(Boolean status) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Theater WHERE status = :status ORDER BY id DESC", Theater.class)
                .setParameter("status", status)
                .getResultList();
    }
}