package com.gurkan.eventdrivenmicroservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockUpdatedEvent {
    private final String id;
    private final Integer stock;
}
