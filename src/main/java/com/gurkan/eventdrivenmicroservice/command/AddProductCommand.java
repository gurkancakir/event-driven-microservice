package com.gurkan.eventdrivenmicroservice.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AddProductCommand {

    @TargetAggregateIdentifier
    private final String id;
    private final BigDecimal price;
    private final Integer stock;
    private final String description;
}
