package com.invillia.acme.orderservice.resource;

import com.invillia.acme.orderservice.config.ResourceRepository;
import com.invillia.acme.orderservice.model.DTO.OrderDTO;
import com.invillia.acme.orderservice.model.Order;
import com.invillia.acme.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
public class OrderResource extends ResourceRepository<Order> {

    private OrderService orderService;

    @Autowired
    public OrderResource(JpaRepository<Order, Long> repository, OrderService orderService) {
        super(repository);
        this.orderService = orderService;
    }


    @Override
    public ResponseEntity<?> findAll() {
        return super.findAll();
    }

    @PostMapping(value = "/store/{idStore}/newOrder")
    public ResponseEntity<?> newOrder(@PathVariable("idStore") Long idStore, @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.saveNewOrder(idStore, orderDTO));
    }

    @PostMapping(value = "/paymentOrder/{idOrder}")
    public ResponseEntity<?> paymentOrder(@PathVariable("idOrder") Long idOrder, @RequestBody LocalDateTime paymentDate ){
        return ResponseEntity.ok(orderService.paymentOrder(idOrder,paymentDate));
    }

    @GetMapping(value = "/refundedOrder/{idOrder}")
    public ResponseEntity<?> refundedOrder(@PathVariable("idOrder") Long idOrder){
        return ResponseEntity.ok(orderService.refundedOrder(idOrder));
    }

    @GetMapping(value = "/refundedOrder/{idOrder}/item/{idItem}")
    public ResponseEntity<?> refundedOrderItem(@PathVariable("idOrder") Long idOrder,@PathVariable("idItem") Long idItem){
        return ResponseEntity.ok(orderService.refundedOrderItem(idOrder, idItem));
    }
}
