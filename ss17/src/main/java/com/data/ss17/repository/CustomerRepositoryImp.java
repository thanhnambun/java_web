package com.data.ss17.repository;

import com.data.ss17.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class CustomerRepositoryImp implements CustomerRepository {
    private SessionFactory sessionFactory;
    public CustomerRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Customer customer = session
                    .createQuery("FROM Customer WHERE username = :username", Customer.class)
                    .setParameter("username", username)
                    .uniqueResult();
            return Optional.ofNullable(customer);
        }
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Customer customer = session
                    .createQuery("FROM Customer WHERE email = :email", Customer.class)
                    .setParameter("email", email)
                    .uniqueResult();
            return Optional.ofNullable(customer);
        }
    }

    @Override
    public boolean save(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
    public void update(Customer customer) {
        sessionFactory.getCurrentSession().update(customer);
    }

    public Customer findById(Long id) {
        return sessionFactory.getCurrentSession().get(Customer.class, id);
    }
    public List<Customer> findAllWithPagination(int page, int size, String search) {
        String hql = "FROM Customer WHERE (:search IS NULL OR :search = '' OR username LIKE :searchPattern OR email LIKE :searchPattern) ORDER BY id DESC";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Customer.class)
                .setParameter("search", search)
                .setParameter("searchPattern", search != null ? "%" + search + "%" : null)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Long countWithSearch(String search) {
        String hql = "SELECT COUNT(*) FROM Customer WHERE (:search IS NULL OR :search = '' OR username LIKE :searchPattern OR email LIKE :searchPattern)";
        return (Long) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter("search", search)
                .setParameter("searchPattern", search != null ? "%" + search + "%" : null)
                .uniqueResult();
    }

    public Long countAll() {
        String hql = "SELECT COUNT(*) FROM Customer";
        return (Long) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .uniqueResult();
    }
}
