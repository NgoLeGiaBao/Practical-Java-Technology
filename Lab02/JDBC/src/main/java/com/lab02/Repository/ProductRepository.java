package com.lab02.Repository;

import com.lab02.Models.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {
    List<Product> listProducts() throws SQLException;
    Product getProductById(int id) throws SQLException;
    int addProduct(String name, double price) throws SQLException;
    boolean updateProduct(int id, String name, double price) throws SQLException;
    boolean deleteProduct(int id) throws SQLException;
}
