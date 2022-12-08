package com.example.onlineshop.security.services;

import com.example.onlineshop.security.models.OrderProduct;
import com.example.onlineshop.security.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderProduct addOrder(OrderProduct orderProduct)
    {
        orderRepository.save(orderProduct);
        return orderProduct;
    }
}
