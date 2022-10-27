package com.gurkan.eventdrivenmicroservice.repository;

import com.gurkan.eventdrivenmicroservice.entity.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderSummary, String> {
}
