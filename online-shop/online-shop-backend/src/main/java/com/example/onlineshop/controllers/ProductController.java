package com.example.onlineshop.controllers;

import com.example.onlineshop.security.models.Product;
import com.example.onlineshop.security.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts()
    {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping("/save")
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @GetMapping("/oneProduct/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable Long id)
    {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }

}
