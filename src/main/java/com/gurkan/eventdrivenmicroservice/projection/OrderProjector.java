package com.gurkan.eventdrivenmicroservice.projection;

import com.gurkan.eventdrivenmicroservice.entity.OrderSummary;
import com.gurkan.eventdrivenmicroservice.event.OrderCreatedEvent;
import com.gurkan.eventdrivenmicroservice.event.StockUpdatedEvent;
import com.gurkan.eventdrivenmicroservice.query.GetOrdersQuery;
import com.gurkan.eventdrivenmicroservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderProjector {

    private final EventGateway eventGateway;
    private final OrderRepository repository;
    @EventHandler
    public void on(OrderCreatedEvent evt){
        repository.save(
                new OrderSummary(
                        evt.getOrderId(),
                        evt.getPrice(),
                        evt.getNumber(),
                        evt.getProductId())
        );

        //Fire a Stock updated event
        StockUpdatedEvent event = new StockUpdatedEvent(evt.getProductId(), evt.getNumber());
        eventGateway.publish(event);
    }

    @QueryHandler
    public List<OrderSummary> handle(GetOrdersQuery qry){
        return repository.findAll();
    }
}
