package com.example.onlineshop.security.services;

import com.example.onlineshop.security.models.Product;
import com.example.onlineshop.security.models.ShopCart;
import com.example.onlineshop.security.repositories.ShopCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopCartService {

    @Autowired
    private ShopCartRepository shopCartRepository;

    public ShopCart addProductInCart(ShopCart shopCart)
    {
        shopCartRepository.save(shopCart);
        return shopCart;
    }

    public List<ShopCart> getAllProductsInShopCart(Long clientId){
        return shopCartRepository.findShopCartByClient_Id(clientId);
    }

    public List<ShopCart> getAllProductsSaved(Long clientId){
        return shopCartRepository.findSavedProductByClient_Id(clientId);
    }
    public Boolean checkProductInShopCart(Long productId, Long clientId)
    {
        Optional<ShopCart> shopCartOptional = shopCartRepository.findShopCartByProductIdAndClientId(productId, clientId);
        return shopCartOptional.isPresent();
    }

    public ShopCart checkIfProductIsSaved(Long productId, Long clientId)
    {
        Optional<ShopCart> shopCartOptional = shopCartRepository.findSavedProductByProductIdAndClientId(productId, clientId);
        return shopCartOptional.orElse(null);
    }

    public ShopCart updateQuantity(ShopCart shopCart, Integer newQuantity){
        Optional<ShopCart> shopCartOptional = shopCartRepository.findById(shopCart.getId());
        if (shopCartOptional.isPresent()) {
            shopCart.setQuantity(newQuantity);
            return shopCartRepository.save(shopCart);
        } else {
            throw new NoSuchElementException(String.valueOf(shopCart));
        }
    }

    public void deleteProductInCart(Long id)
    {
        Optional<ShopCart> shopCartOptional = shopCartRepository.findById(id);
        shopCartOptional.ifPresent(shopCart -> shopCartRepository.delete(shopCart));
    }

}
