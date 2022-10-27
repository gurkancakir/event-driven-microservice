package com.gurkan.eventdrivenmicroservice.controller;

import com.gurkan.eventdrivenmicroservice.command.AddProductCommand;
import com.gurkan.eventdrivenmicroservice.dto.ProductDto;
import com.gurkan.eventdrivenmicroservice.query.GetProductsQuery;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/addProduct")
    public void handle(@RequestBody ProductDto summary){
        AddProductCommand cmd = new AddProductCommand(
                summary.getId(),
                summary.getPrice(),
                summary.getStock(),
                summary.getDescription()
        );
        commandGateway.sendAndWait(cmd);
    }

    @GetMapping("/products")
    public List<ProductDto> getProducts(){
        List<ProductDto> productDtoList = queryGateway.query(new GetProductsQuery(),
                        ResponseTypes.multipleInstancesOf(ProductDto.class))
                .join();
        return productDtoList;
    }
}
