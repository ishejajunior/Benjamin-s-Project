package com.benjamin.ecommerce.repository;

import com.benjamin.ecommerce.model.Order;
import com.benjamin.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findByStatus(Order.OrderStatus status);
}