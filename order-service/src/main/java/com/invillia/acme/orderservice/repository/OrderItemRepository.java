package com.invillia.acme.orderservice.repository;

import com.invillia.acme.orderservice.model.Order;
import com.invillia.acme.orderservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findByOrOrder(Order order);
}
