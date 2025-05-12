package com.benjamin.ecommerce.service;

import com.benjamin.ecommerce.model.Order;
import com.benjamin.ecommerce.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order placeOrder(Order order);

    Optional<Order> findOrderById(Long id);

    List<Order> findOrdersByUser(User user);

    List<Order> findAllOrders();

    List<Order> findOrdersByStatus(Order.OrderStatus status);

    Order updateOrderStatus(Long id, Order.OrderStatus status);
}