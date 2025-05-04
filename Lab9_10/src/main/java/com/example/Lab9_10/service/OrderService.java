package com.example.Lab9_10.service;

import com.example.Lab9_10.DTO.OrderDTO;
import com.example.Lab9_10.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order addOrder(OrderDTO order);
    Order getOrderById(Long id);
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);
}
