package com.example.onlineshop.security.repositories;

import com.example.onlineshop.security.models.Comments;
import com.example.onlineshop.security.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderProduct, Long> {

    @Query("SELECT op FROM OrderProduct op WHERE op.client.id = :clientId ORDER BY op.orderDate DESC")
    List<OrderProduct> findOrderProductsByClientId(Long clientId);

    @Query("SELECT op FROM OrderProduct op WHERE op.product.designer.id = :designerId ORDER BY op.orderDate")
    List<OrderProduct> findOrderProductsByDesignerId(Long designerId);
}
