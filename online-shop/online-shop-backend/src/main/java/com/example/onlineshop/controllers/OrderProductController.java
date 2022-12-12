package com.example.onlineshop.controllers;

import com.example.onlineshop.security.models.OrderProduct;
import com.example.onlineshop.security.services.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getProductsClient/{clientId}")
    public ResponseEntity<List<OrderProduct>> getProductsOrderedByClientId(@PathVariable Long clientId)
    {
        return ResponseEntity.ok(orderProductService.getProductsOrderedByClientId(clientId));
    }

    @GetMapping("/getProductsDesigner/{designerId}")
    public ResponseEntity<List<OrderProduct>> getProductsOrderedByDesignerId(@PathVariable Long designerId)
    {
        return ResponseEntity.ok(orderProductService.getProductsOrderedByDesignerId(designerId));
    }

    @PatchMapping("/finalizeOrder/{finalized}")
    public ResponseEntity<OrderProduct> updateOrderProduct(@RequestBody OrderProduct orderProduct, @PathVariable Boolean finalized)
    {
        return ResponseEntity.ok(orderProductService.updateOrderProduct(orderProduct,finalized));
    }
}
