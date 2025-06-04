package com.data.ss17.repository;

import com.data.ss17.model.Customer;
import com.data.ss17.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImp implements ProductRepository {
    private SessionFactory sessionFactory;
    public ProductRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("FROM Product", Product.class);
            return query.getResultList();
        }
    }

    @Override
    public Optional<Product> findById(Long id_in) {
        try (Session session = sessionFactory.openSession()) {
            Product product = session
                    .createQuery("FROM Product WHERE  id =:id_in", Product.class)
                    .setParameter("id_in", id_in)
                    .uniqueResult();
            return Optional.ofNullable(product);
        }
    }
    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    public void update(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }

    public void delete(Long id) {
        Optional<Product> product = findById(id);
        if (product.isPresent()) {
            sessionFactory.getCurrentSession().delete(product);
        }
    }

    public List<Product> findAllWithPagination(int page, int size, String search) {
        String hql = "FROM Product WHERE (:search IS NULL OR :search = '' OR productName LIKE :searchPattern) ORDER BY id DESC";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Product.class)
                .setParameter("search", search)
                .setParameter("searchPattern", search != null ? "%" + search + "%" : null)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Long countWithSearch(String search) {
        String hql = "SELECT COUNT(*) FROM Product WHERE (:search IS NULL OR :search = '' OR productName LIKE :searchPattern)";
        return (Long) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter("search", search)
                .setParameter("searchPattern", search != null ? "%" + search + "%" : null)
                .uniqueResult();
    }

    public Long countAll() {
        String hql = "SELECT COUNT(*) FROM Product";
        return (Long) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .uniqueResult();
    }
}