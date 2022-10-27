package com.gurkan.eventdrivenmicroservice.aggregate;

import com.gurkan.eventdrivenmicroservice.command.AddProductCommand;
import com.gurkan.eventdrivenmicroservice.command.UpdateStockCommand;
import com.gurkan.eventdrivenmicroservice.event.ProductAddedEvent;
import com.gurkan.eventdrivenmicroservice.event.StockUpdatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
public class Product {

    @AggregateIdentifier
    private String id;
    private BigDecimal price;
    private Integer stock;
    private String description;

    @CommandHandler
    public Product(AddProductCommand cmd){
        AggregateLifecycle.apply(new ProductAddedEvent(
                        cmd.getId(),
                        cmd.getPrice(),
                        cmd.getStock(),
                        cmd.getDescription()
                )
        );
    }

    @CommandHandler
    public void updateStock(UpdateStockCommand cmd){
        if(this.stock >= cmd.getStock()){
            AggregateLifecycle.apply(new StockUpdatedEvent(
                    cmd.getId(),
                    cmd.getStock())
            );
        }
        else {
            throw new RuntimeException("Out of Stock!");
        }
    }

    @EventSourcingHandler
    public void on(StockUpdatedEvent evt){
        id = evt.getId();
        stock = stock - evt.getStock();
    }

    @EventSourcingHandler
    public void on(ProductAddedEvent evt){
        id = evt.getId();
        price = evt.getPrice();
        stock = evt.getStock();
        description = evt.getDescription();
    }
}
