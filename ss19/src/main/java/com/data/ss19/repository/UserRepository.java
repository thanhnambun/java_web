package com.data.ss19.repository;

import com.data.ss19.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public void saveOrUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    public User findByUsername(String username) {
        String hql = "FROM User WHERE username = :username";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, User.class)
                .setParameter("username", username)
                .uniqueResult();
    }

    public User findByEmail(String email) {
        String hql = "FROM User WHERE email = :email";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, User.class)
                .setParameter("email", email)
                .uniqueResult();
    }

    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public User findById(Integer id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    public List<User> findAll() {
        String hql = "FROM User ORDER BY id DESC";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, User.class)
                .getResultList();
    }

    public List<User> findAllWithPagination(int page, int size, String search) {
        String hql = "FROM User WHERE (:search IS NULL OR :search = '' OR username LIKE :searchPattern OR email LIKE :searchPattern OR phoneNumber LIKE :searchPattern) ORDER BY id DESC";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, User.class)
                .setParameter("search", search)
                .setParameter("searchPattern", search != null ? "%" + search + "%" : null)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public long countWithSearch(String search) {
        String hql = "SELECT COUNT(*) FROM User WHERE (:search IS NULL OR :search = '' OR username LIKE :searchPattern OR email LIKE :searchPattern OR phoneNumber LIKE :searchPattern)";
        return (Long) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter("search", search)
                .setParameter("searchPattern", search != null ? "%" + search + "%" : null)
                .uniqueResult();
    }

    public void delete(Integer id) {
        User user = findById(id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    public List<User> findByName(String name) {
        String hql = "FROM User WHERE username LIKE :name";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, User.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public long countAll() {
        String hql = "SELECT COUNT(*) FROM User";
        return (Long) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .uniqueResult();
    }


}