package com.data.ss19.repository;

import com.data.ss19.model.Seat;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Seat seat) {
        sessionFactory.getCurrentSession().save(seat);
    }

    public void saveAll(List<Seat> seats) {
        for (Seat seat : seats) {
            save(seat);
        }
    }

    public List<Seat> findByScreenRoomId(Long screenRoomId) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Seat WHERE screenRoom.id = :screenRoomId ORDER BY seatName", Seat.class)
                .setParameter("screenRoomId", screenRoomId)
                .getResultList();
    }

    public long countByScreenRoomId(Long screenRoomId) {
        String hql = "SELECT COUNT(*) FROM Seat WHERE screenRoom.id = :screenRoomId";
        return (Long) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter("screenRoomId", screenRoomId)
                .uniqueResult();
    }
}