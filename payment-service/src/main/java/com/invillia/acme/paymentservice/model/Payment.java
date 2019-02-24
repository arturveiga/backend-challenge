package com.invillia.acme.paymentservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.invillia.acme.paymentservice.model.enums.PaymentStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
public class Payment extends DefaultEntity{

    private PaymentStatus status;
    @Column(name = "credit_card")
    @JsonProperty(value = "credit_card")
    private String creditCard;
    @Column(name = "payment_date")
    @JsonProperty(value = "payment_date")
    private LocalDateTime paymentDate;
    private Long idOrder;
}
