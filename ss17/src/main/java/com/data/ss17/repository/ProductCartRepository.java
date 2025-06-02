package com.data.ss17.repository;

import com.data.ss17.model.ProductCart;

import java.util.List;

public interface ProductCartRepository {
    ProductCart findByCustomerAndProduct(Long customerId, Long productId);
    void save(ProductCart cart);
    void update(ProductCart cart);
    List<ProductCart> findByCustomerId(Long customerId);
    ProductCart findById(Long cartId);
    void delete(ProductCart cart);
    void clearCart(Long customerId);
}
