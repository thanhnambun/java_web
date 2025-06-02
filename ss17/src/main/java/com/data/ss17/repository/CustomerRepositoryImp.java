package com.data.ss17.repository;

import com.data.ss17.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

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
}
