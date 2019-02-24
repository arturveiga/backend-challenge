package com.invillia.acme.orderservice.model.enums;

import lombok.Getter;
import lombok.Setter;

public enum OrderStatus {
    AWAIT_PAYMENT(0),
    PAID(1),
    REFUNDED(2);

    @Getter@Setter
    private final Integer id;

    OrderStatus(Integer id) {
        this.id = id;
    }
}
