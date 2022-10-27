package com.gurkan.eventdrivenmicroservice.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class OrderSummary implements Serializable {
    @Id
    private String id;
    private BigDecimal price;
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    private String productId;

    public OrderSummary(String id, BigDecimal price, Integer number, String productId) {
        this.id = id;
        this.price = price;
        this.number = number;
        this.productId = productId;
    }
}
