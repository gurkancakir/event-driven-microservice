package com.gurkan.eventdrivenmicroservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductAddedEvent {
    private final String id;
    private final BigDecimal price;
    private final Integer stock;
    private final String description;
}
