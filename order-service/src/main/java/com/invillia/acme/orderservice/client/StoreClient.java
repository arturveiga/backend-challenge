package com.invillia.acme.orderservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@FeignClient(url = "http://localhost:8080/acme/stores",name = "store-service")
public interface StoreClient {
    @GetMapping("/{id}")
    Optional<Object> findById(@PathVariable("id") Long id);
}
