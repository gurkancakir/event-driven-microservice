package com.gurkan.eventdrivenmicroservice.projection;

import com.gurkan.eventdrivenmicroservice.entity.Product;
import com.gurkan.eventdrivenmicroservice.event.ProductAddedEvent;
import com.gurkan.eventdrivenmicroservice.event.StockUpdatedEvent;
import com.gurkan.eventdrivenmicroservice.query.GetProductsQuery;
import com.gurkan.eventdrivenmicroservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductProjector {

    private final ProductRepository productRepository;

    @EventHandler
    public  void on(ProductAddedEvent evt){
        Product product = new Product(evt.getId(), evt.getPrice(), evt.getStock(), evt.getDescription());
        productRepository.save(product);
    }

    @EventHandler
    public void on(StockUpdatedEvent evt){
        Product summary = productRepository.findById(evt.getId()).orElse(null);
        summary.setStock(summary.getStock() - evt.getStock());
        productRepository.save(summary);
    }

    @QueryHandler
    public List<Product> handle(GetProductsQuery query){
        return productRepository.findAll();
    }


}
