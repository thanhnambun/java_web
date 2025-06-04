package com.data.ss19.repository;

import com.data.ss19.model.ScreenRoom;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScreenRoomRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<ScreenRoom> findByStatus(Boolean status) {
        String hql = "FROM ScreenRoom sr " +
                "LEFT JOIN FETCH sr.theater " +
                "WHERE sr.status = :status ORDER BY sr.id DESC";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, ScreenRoom.class)
                .setParameter("status", status)
                .getResultList();
    }

    public ScreenRoom findById(Long id) {
        String hql = "FROM ScreenRoom sr " +
                "LEFT JOIN FETCH sr.theater " +
                "LEFT JOIN FETCH sr.seats " +
                "WHERE sr.id = :id";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, ScreenRoom.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    public void save(ScreenRoom screenRoom) {
        sessionFactory.getCurrentSession().save(screenRoom);
    }

    public void update(ScreenRoom screenRoom) {
        sessionFactory.getCurrentSession().update(screenRoom);
    }

    public void delete(Long id) {
        ScreenRoom screenRoom = findById(id);
        if (screenRoom != null) {
            screenRoom.setStatus(false);
            update(screenRoom);
        }
    }

    public List<ScreenRoom> findAll() {
        String hql = "FROM ScreenRoom sr " +
                "LEFT JOIN FETCH sr.theater " +
                "ORDER BY sr.id DESC";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, ScreenRoom.class)
                .getResultList();
    }
}