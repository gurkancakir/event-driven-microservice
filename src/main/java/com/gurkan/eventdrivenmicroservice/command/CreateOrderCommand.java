package com.gurkan.eventdrivenmicroservice.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
    private final BigDecimal price;
    private final Integer number;
    private final String productId;
}
