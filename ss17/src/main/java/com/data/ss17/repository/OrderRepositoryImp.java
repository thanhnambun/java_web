package com.data.ss17.repository;

import com.data.ss17.model.Orders;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class OrderRepositoryImp implements OrderRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Orders order) {
        sessionFactory.getCurrentSession().save(order);
    }

    public List<Orders> findByCustomerIdWithPagination(Long customerId, int page, int size) {
        String hql = "FROM Orders WHERE customerId = :customerId ORDER BY createdAt DESC";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Orders.class)
                .setParameter("customerId", customerId)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Long countByCustomerId(Long customerId) {
        String hql = "SELECT COUNT(*) FROM Orders WHERE customerId = :customerId";
        return (Long) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter("customerId", customerId)
                .uniqueResult();
    }

    public Orders findById(Long id) {
        return sessionFactory.getCurrentSession().get(Orders.class, id);
    }

    public void update(Orders order) {
        sessionFactory.getCurrentSession().update(order);
    }
    public List<Orders> findAllWithFilter(int page, int size, String search, String status, String startDate, String endDate) {
        StringBuilder hql = new StringBuilder("FROM Orders WHERE 1=1");

        if (search != null && !search.trim().isEmpty()) {
            hql.append(" AND recipientName LIKE :search");
        }
        if (status != null && !status.trim().isEmpty()) {
            hql.append(" AND status = :status");
        }
        if (startDate != null && !startDate.trim().isEmpty()) {
            hql.append(" AND DATE(createdAt) >= :startDate");
        }
        if (endDate != null && !endDate.trim().isEmpty()) {
            hql.append(" AND DATE(createdAt) <= :endDate");
        }
        hql.append(" ORDER   BY createdAt DESC");

        org.hibernate.query.Query<Orders> query = sessionFactory.getCurrentSession()
                .createQuery(hql.toString(), Orders.class);

        if (search != null && !search.trim().isEmpty()) {
            query.setParameter("search", "%" + search + "%");
        }
        if (status != null && !status.trim().isEmpty()) {
            query.setParameter("status", status);
        }
        if (startDate != null && !startDate.trim().isEmpty()) {
            try {
                Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                query.setParameter("startDate", start);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (endDate != null && !endDate.trim().isEmpty()) {
            try {
                Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
                query.setParameter("endDate", end);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return query.setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Long countWithFilter(String search, String status, String startDate, String endDate) {
        StringBuilder hql = new StringBuilder("SELECT COUNT(*) FROM Orders WHERE 1=1");

        if (search != null && !search.trim().isEmpty()) {
            hql.append(" AND recipientName LIKE :search");
        }
        if (status != null && !status.trim().isEmpty()) {
            hql.append(" AND status = :status");
        }
        if (startDate != null && !startDate.trim().isEmpty()) {
            hql.append(" AND createdAt >= :startDate");
        }
        if (endDate != null && !endDate.trim().isEmpty()) {
            hql.append(" AND createdAt <= :endDate");
        }

        org.hibernate.query.Query query = sessionFactory.getCurrentSession()
                .createQuery(hql.toString());

        if (search != null && !search.trim().isEmpty()) {
            query.setParameter("search", "%" + search + "%");
        }
        if (status != null && !status.trim().isEmpty()) {
            query.setParameter("status", status);
        }
        if (startDate != null && !startDate.trim().isEmpty()) {
            try {
                Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                query.setParameter("startDate", start);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (endDate != null && !endDate.trim().isEmpty()) {
            try {
                Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
                query.setParameter("endDate", end);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return (Long) query.uniqueResult();
    }

    public Long countAll() {
        String hql = "SELECT COUNT(*) FROM Orders";
        return (Long) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .uniqueResult();
    }

    public double getTotalRevenue() {
        String hql = "SELECT SUM(totalMoney) FROM Orders WHERE status = 'COMPLETED'";
        BigDecimal result = (BigDecimal) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .uniqueResult();
        return result != null ? result.doubleValue() : 0.0;
    }
}
