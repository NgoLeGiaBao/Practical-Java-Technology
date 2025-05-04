package com.example.Lab9_10.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Table(name = "orders")
@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private Double totalSellingPrice;

    @ManyToMany
    private List<Product> products;
}