package com.example.onlineshop.security.services;

import com.example.onlineshop.security.models.OrderProduct;
import com.example.onlineshop.security.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderProductService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderProduct addOrder(OrderProduct orderProduct)
    {
        orderRepository.save(orderProduct);
        return orderProduct;
    }

    public List<OrderProduct> getProductsOrderedByClientId(Long clientId){
        return orderRepository.findOrderProductsByClientId(clientId);
    }

    public List<OrderProduct> getProductsOrderedByDesignerId(Long designerId){
        return orderRepository.findOrderProductsByDesignerId(designerId);
    }

    public OrderProduct updateOrderProduct(OrderProduct orderProduct, Boolean finalized)
    {
        Optional<OrderProduct> orderProductOptional = orderRepository.findById(orderProduct.getId());
        if(orderProductOptional.isPresent())
        {
            orderProduct.setFinalized(finalized);
            return orderRepository.save(orderProduct);
        }
        else
        {
            throw new NoSuchElementException(String.valueOf(orderProduct));
        }
    }
}
