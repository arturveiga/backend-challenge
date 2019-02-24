package com.invillia.acme.paymentservice.model.enums;

import lombok.Getter;
import lombok.Setter;

public enum PaymentStatus {
    REFUNDED(0),
    PAID(1);

    @Getter@Setter
    private final Integer id;

    PaymentStatus(Integer id) {
        this.id = id;
    }
}
