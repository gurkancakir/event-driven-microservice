package com.gurkan.eventdrivenmicroservice.controller;

import com.gurkan.eventdrivenmicroservice.command.CreateOrderCommand;
import com.gurkan.eventdrivenmicroservice.entity.OrderSummary;
import com.gurkan.eventdrivenmicroservice.query.GetOrdersQuery;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
public class OrderController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/createOrder")
    public void handle(@RequestBody OrderSummary summary){
        CreateOrderCommand cmd = new CreateOrderCommand(
                summary.getId(),
                summary.getPrice(),
                summary.getNumber(),
                summary.getProductId()
        );
        commandGateway.send(cmd);
    }


    @GetMapping("/orders")
    public CompletableFuture<List<OrderSummary>> getOrders(){
        return queryGateway.query(new GetOrdersQuery(), ResponseTypes.multipleInstancesOf(OrderSummary.class));
    }
}
