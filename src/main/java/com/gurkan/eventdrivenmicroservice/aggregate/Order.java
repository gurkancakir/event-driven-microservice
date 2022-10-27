package com.gurkan.eventdrivenmicroservice.aggregate;

import com.gurkan.eventdrivenmicroservice.command.CreateOrderCommand;
import com.gurkan.eventdrivenmicroservice.event.OrderCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
public class Order {
    @AggregateIdentifier
    private String orderId;
    private BigDecimal price;
    private Integer number;
    private String productId;

    @CommandHandler
    public Order(CreateOrderCommand cmd){
        AggregateLifecycle.apply(
                new OrderCreatedEvent(cmd.getOrderId(), cmd.getPrice(), cmd.getNumber(), cmd.getProductId())
        );
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent evt){
        orderId = evt.getOrderId();
        price = evt.getPrice();
        number = evt.getNumber();
        productId = evt.getProductId();
    }
}
