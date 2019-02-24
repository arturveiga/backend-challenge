package com.invillia.acme.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@FeignClient(url = "http://localhost:8080/acme/orders",name = "order-service")
public interface OrderClient {
    @GetMapping("/{id}")
    Optional<Object> findById(@PathVariable("id") Long id);
    @PostMapping(value = "/paymentOrder/{idOrder}")
    Optional<Object> paymentOrder(@PathVariable("idOrder") Long idOrder, @RequestBody LocalDateTime paymentDate );
}
