package com.gurkan.eventdrivenmicroservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderCreatedEvent {

    private final String orderId;
    private final BigDecimal price;
    private final Integer number;
    private final String productId;
}
