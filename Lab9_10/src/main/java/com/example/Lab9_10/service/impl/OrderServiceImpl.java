package com.example.Lab9_10.service.impl;

import com.example.Lab9_10.DTO.OrderDTO;
import com.example.Lab9_10.entity.Order;
import com.example.Lab9_10.repository.OrderRepository;
import com.example.Lab9_10.repository.ProductRepository;
import com.example.Lab9_10.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order addOrder(OrderDTO order) {
       Order O = new Order();
       O.setOrderNumber(order.getOrderNumber());
       O.setTotalSellingPrice(order.getTotalSellingPrice());
       O.setProducts(productRepository.findAllById(order.getProductIds()));
       return orderRepository.save(O);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        Order existingOrder = getOrderById(id);
        existingOrder.setOrderNumber(order.getOrderNumber());
        existingOrder.setTotalSellingPrice(order.getTotalSellingPrice());
        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
