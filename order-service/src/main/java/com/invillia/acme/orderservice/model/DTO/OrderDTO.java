package com.invillia.acme.orderservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.invillia.acme.orderservice.model.OrderItem;
import com.invillia.acme.orderservice.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    @Getter@Setter
    private Long id;
    @Getter@Setter
    private String address;
    @JsonProperty(value= "cofirmation_date")
    private LocalDateTime confirmationDate;
    @Getter@Setter
    private OrderStatus status;
    @Getter@Setter
    private List<OrderItem> items;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", confirmationDate=" + confirmationDate +
                ", status=" + status +
                ", items=" + items +
                '}';
    }
}
