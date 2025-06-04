package com.data.ss17.repository;

import com.data.ss17.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product>  findAll();
    Optional<Product> findById(Long id);
    List<Product> findAllWithPagination(int page, int size, String search);
    void save(Product product);
    void update(Product product);
    void delete(Long id);
    Long countWithSearch(String search);
    Long countAll();
}