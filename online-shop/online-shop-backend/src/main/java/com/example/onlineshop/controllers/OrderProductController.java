package com.example.onlineshop.controllers;

import com.example.onlineshop.security.models.OrderProduct;
import com.example.onlineshop.security.services.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/orderProduct")
@RequiredArgsConstructor
public class OrderProductController {

    @Autowired
    private OrderProductService orderProductService;

    @PostMapping("/save")
    public ResponseEntity<OrderProduct> addOrder(@RequestBody OrderProduct orderProduct)
    {
        return ResponseEntity.ok(orderProductService.addOrder(orderProduct));
    }
}
