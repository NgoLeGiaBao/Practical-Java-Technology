package com.example.Lab9_10.service;

import com.example.Lab9_10.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product getProductById(Long id);
    Product replaceProduct(Long id, Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
