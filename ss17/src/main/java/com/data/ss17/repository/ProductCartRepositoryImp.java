package com.data.ss17.repository;

import com.data.ss17.model.ProductCart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductCartRepositoryImp implements ProductCartRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public ProductCart findByCustomerAndProduct(Long customerId, Long productId) {
        String hql = "FROM ProductCart WHERE customerId = :customerId AND productId = :productId";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, ProductCart.class)
                .setParameter("customerId", customerId)
                .setParameter("productId", productId)
                .uniqueResult();
    }

    public void save(ProductCart cart) {
        sessionFactory.getCurrentSession().save(cart);
    }

    public void update(ProductCart cart) {
        sessionFactory.getCurrentSession().update(cart);
    }

    public List<ProductCart> findByCustomerId(Long customerId) {
        String hql = "FROM ProductCart WHERE customerId = :customerId";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, ProductCart.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public ProductCart findById(Long cartId) {
        return sessionFactory.getCurrentSession().get(ProductCart.class, cartId);
    }

    public void delete(ProductCart cart) {
        sessionFactory.getCurrentSession().delete(cart);
    }

    public void clearCart(Long customerId) {
        String hql = "DELETE FROM ProductCart WHERE customerId = :customerId";
        sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter("customerId", customerId)
                .executeUpdate();
    }
}