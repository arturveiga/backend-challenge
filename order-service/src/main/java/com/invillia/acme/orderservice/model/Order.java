package com.invillia.acme.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invillia.acme.orderservice.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order extends DefaultEntity {
    @NotEmpty(message = "Address is Required")
    private String address;
    @Column(name = "cofirmation_date")
    private LocalDateTime confirmationDate;
    private OrderStatus status;
    private Long idStore;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    public Order(Long id) {
        this.setId(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "address='" + address + '\'' +
                ", confirmationDate=" + confirmationDate +
                ", status=" + status +
                ", idStore=" + idStore +
                ", items=" + items +
                '}';
    }
}
