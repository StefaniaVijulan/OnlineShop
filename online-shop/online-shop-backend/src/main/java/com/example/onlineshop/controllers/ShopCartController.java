package com.example.onlineshop.controllers;


import com.example.onlineshop.security.models.Product;
import com.example.onlineshop.security.models.ShopCart;
import com.example.onlineshop.security.services.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/shopCart")
public class ShopCartController {

    @Autowired
    private ShopCartService shopCartService;

    @PostMapping("/addProductInCart")
    public ResponseEntity<ShopCart> addProductInCart(@RequestBody ShopCart shopCart)
    {
        return ResponseEntity.ok(shopCartService.addProductInCart(shopCart));
    }

    @GetMapping("/allProducts/{clientId}")
    public ResponseEntity<List<ShopCart>> getAllProductsInShopCart(@PathVariable Long clientId)
    {
        return ResponseEntity.ok(shopCartService.getAllProductsInShopCart(clientId));
    }

    @GetMapping("/savedProducts/{clientId}")
    public ResponseEntity<List<ShopCart>> getAllProductsSaved(@PathVariable Long clientId)
    {
        return ResponseEntity.ok(shopCartService.getAllProductsSaved(clientId));
    }


    @PatchMapping("/updateQuantity/{newQuantity}")
    public ResponseEntity<ShopCart> updateQuantity(@RequestBody ShopCart shopCart,@PathVariable Integer newQuantity)
    {
        return ResponseEntity.ok(shopCartService.updateQuantity(shopCart, newQuantity));
    }

    @GetMapping("/checkProductInShopCart/{productId}/{clientId}")
    public ResponseEntity<Boolean> checkProductInShopCart(@PathVariable Long productId, @PathVariable Long clientId)
    {
        return ResponseEntity.ok(shopCartService.checkProductInShopCart(productId, clientId));
    }

    @GetMapping("/checkIfProductIsSaved/{productId}/{clientId}")
    public ResponseEntity<ShopCart> checkIfProductIsSaved(@PathVariable Long productId, @PathVariable Long clientId)
    {
        return ResponseEntity.ok(shopCartService.checkIfProductIsSaved(productId, clientId));
    }

    @DeleteMapping("/deleteProductInCart/{id}")
    public void deleteProductInCart(@PathVariable Long id)
    {
        shopCartService.deleteProductInCart(id);
    }

}
