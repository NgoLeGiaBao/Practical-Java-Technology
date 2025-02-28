package com.lab02;

import com.lab02.DAO.ProductDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductDAO productDAO = new ProductDAO();
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Read product list");
            System.out.println("2. Read a product by input ID");
            System.out.println("3. Add a new product");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    try {
                        productDAO.listProducts().forEach(System.out::println);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    System.out.print("Enter product ID: ");
                    int id = scanner.nextInt();
                    try {
                        System.out.println(productDAO.getProductById(id));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    try {
                        System.out.println("Product added with ID: " + productDAO.addProduct(name, price));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    System.out.print("Enter product ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new price: ");
                    price = scanner.nextDouble();
                    try {
                        System.out.println(productDAO.updateProduct(id, name, price) ? "Product updated." : "Product not found.");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 5:
                    System.out.print("Enter product ID: ");
                    id = scanner.nextInt();
                    try {
                        System.out.println(productDAO.deleteProduct(id) ? "Product deleted." : "Product not found.");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}