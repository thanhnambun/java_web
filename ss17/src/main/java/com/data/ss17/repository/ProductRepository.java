package com.data.ss17.repository;

import com.data.ss17.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product>  findAll();
    Optional<Product> findById(Long id);
}
