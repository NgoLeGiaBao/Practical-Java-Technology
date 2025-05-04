package com.example.Lab9_10.repository;

import com.example.Lab9_10.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}