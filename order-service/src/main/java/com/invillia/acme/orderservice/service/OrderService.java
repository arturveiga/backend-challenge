package com.invillia.acme.orderservice.service;

import com.invillia.acme.orderservice.client.PaymentClient;
import com.invillia.acme.orderservice.client.StoreClient;
import com.invillia.acme.orderservice.model.DTO.OrderDTO;
import com.invillia.acme.orderservice.model.Order;
import com.invillia.acme.orderservice.model.OrderItem;
import com.invillia.acme.orderservice.model.enums.OrderStatus;
import com.invillia.acme.orderservice.repository.OrderItemRepository;
import com.invillia.acme.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private StoreClient storeClient;
    private PaymentClient paymentClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, StoreClient storeClient, PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.storeClient = storeClient;
        this.paymentClient = paymentClient;
    }

    public Order saveNewOrder(Long idStore, OrderDTO orderDTO) {
        try {
            Order order = new Order(orderDTO.getAddress(), null, OrderStatus.AWAIT_PAYMENT, idStore, orderDTO.getItems());
            if (!storeClient.findById(idStore).isPresent()) {
                throw new RuntimeException("Store invalid");
            }
            // Save Order
            // Save Items
            orderRepository.save(order);
            order.getItems().forEach(orderItem -> {
                orderItem.setOrder(order);
                orderItem.setRefunded(false);
                orderItemRepository.save(orderItem);
            });
            orderDTO.setId(order.getId());

            return order;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Order paymentOrder(Long idOrder, LocalDateTime paymentDate) {
        try {
            Order order = orderRepository.findById(idOrder).get();
            Optional.ofNullable(order).orElseThrow(Exception::new);
            order.setConfirmationDate(paymentDate);
            order.setStatus(OrderStatus.PAID);
            orderRepository.save(order);
            return order;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public Order refundedOrder(Long idOrder) {
        try {
            Order order = notAvailableRefund(idOrder);

            //Refunded items
            orderItemRepository.findByOrOrder(order).forEach(orderItem -> {
                orderItem.setRefunded(true);
                orderItemRepository.save(orderItem);
            });
            //Refunded Order
            order.setStatus(OrderStatus.REFUNDED);
            orderRepository.save(order);

            //Refunded Payment
            paymentClient.refundedPayment(idOrder);
            return order;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Order refundedOrderItem(Long idOrder,Long idItem) {
        try {
            Order order = notAvailableRefund(idOrder);

            OrderItem item = orderItemRepository.findById(idItem).get();
            item.setRefunded(true);
            orderItemRepository.save(item);

            return order;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Order notAvailableRefund(Long idOrder) {
        Order order = orderRepository.findById(idOrder).get();
        if (order.getStatus().getId().equals(OrderStatus.AWAIT_PAYMENT.getId()) || LocalDateTime.now().isAfter(order.getConfirmationDate().plusDays(10))) {
            throw new RuntimeException("Refund is not allowed.");
        }
        return order;
    }
}
