package com.gurkan.eventdrivenmicroservice.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class UpdateStockCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final Integer stock;
}
