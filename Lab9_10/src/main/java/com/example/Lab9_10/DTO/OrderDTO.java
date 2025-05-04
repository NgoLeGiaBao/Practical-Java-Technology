package com.example.Lab9_10.DTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String orderNumber;
    private Double totalSellingPrice;


    private List<Long> productIds;
}
