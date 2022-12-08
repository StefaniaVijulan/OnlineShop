package com.example.onlineshop.security.repositories;

import com.example.onlineshop.security.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderProduct, Long> {
}
