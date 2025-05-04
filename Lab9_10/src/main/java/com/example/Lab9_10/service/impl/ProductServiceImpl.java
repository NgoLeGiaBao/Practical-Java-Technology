package com.example.Lab9_10.service.impl;

import com.example.Lab9_10.entity.Product;
import com.example.Lab9_10.repository.ProductRepository;
import com.example.Lab9_10.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        if (product.getName() != null) existingProduct.setName(product.getName());
        if (product.getPrice() != null) existingProduct.setPrice(product.getPrice());
        if (product.getIllustration() != null) existingProduct.setIllustration(product.getIllustration());
        if (product.getDescription() != null) existingProduct.setDescription(product.getDescription());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}