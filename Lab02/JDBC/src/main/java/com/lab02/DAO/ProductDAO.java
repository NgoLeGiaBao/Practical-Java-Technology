package com.lab02.DAO;
import com.lab02.Repository.ProductRepository;
import com.lab02.Models.Product;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO implements ProductRepository {
    private Connection connection;

    public ProductDAO() {
        try {
            Properties props = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties");
            if (input == null) {
                throw new RuntimeException("Cannot find db.properties file");
            }
            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");

            connection = DriverManager.getConnection(url, user, "");
            createDatabaseAndTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDatabaseAndTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS ProductManagement");
        stmt.executeUpdate("USE ProductManagement");
        stmt.executeUpdate("DROP TABLE IF EXISTS Product");
        stmt.executeUpdate("CREATE TABLE Product (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), price DOUBLE)");
    }

    public List<Product> listProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
        while (rs.next()) {
            products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
        }
        return products;
    }

    public Product getProductById(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Product WHERE id = ?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        return rs.next() ? new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")) : null;
    }

    public int addProduct(String name, double price) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Product (name, price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, name);
        pstmt.setDouble(2, price);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        return rs.next() ? rs.getInt(1) : -1;
    }

    public boolean updateProduct(int id, String name, double price) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("UPDATE Product SET name = ?, price = ? WHERE id = ?");
        pstmt.setString(1, name);
        pstmt.setDouble(2, price);
        pstmt.setInt(3, id);
        return pstmt.executeUpdate() > 0;
    }

    public boolean deleteProduct(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Product WHERE id = ?");
        pstmt.setInt(1, id);
        return pstmt.executeUpdate() > 0;
    }
}