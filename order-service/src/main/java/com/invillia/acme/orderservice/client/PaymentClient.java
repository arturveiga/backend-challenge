package com.invillia.acme.orderservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@FeignClient(url = "http://localhost:8080/acme/payments",name = "payment-service")
public interface PaymentClient {
    @GetMapping(value = "/refundedPayment/{idOrder}")
    Optional<Object>refundedPayment(@PathVariable("idOrder") Long idOrder);
}
