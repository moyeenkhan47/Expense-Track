package com.expenses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenses.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
}