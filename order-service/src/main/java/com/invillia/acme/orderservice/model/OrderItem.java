package com.invillia.acme.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Data
@Table(name = "order_items")
@JsonIgnoreProperties({"order"})
public class OrderItem extends DefaultEntity {

    private String description;
    @JsonProperty(value = "unit_price")
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    private Integer quantity;
    private Boolean refunded;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
