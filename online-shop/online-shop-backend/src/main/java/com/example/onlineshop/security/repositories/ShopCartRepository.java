package com.example.onlineshop.security.repositories;

import com.example.onlineshop.security.models.ShopCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopCartRepository extends JpaRepository<ShopCart, Long> {

    @Query("SELECT sc FROM ShopCart sc WHERE sc.product.id = :productId AND sc.client.id = :clientId AND sc.savedProduct = false")
    Optional<ShopCart> findShopCartByProductIdAndClientId(Long productId, Long clientId);

    @Query("SELECT sc FROM ShopCart sc WHERE sc.client.id = :clientId AND sc.savedProduct = false")
    List<ShopCart> findShopCartByClient_Id(Long clientId);

    @Query("SELECT sc FROM ShopCart sc WHERE sc.product.id = :productId AND sc.client.id = :clientId AND sc.savedProduct = true")
    Optional<ShopCart> findSavedProductByProductIdAndClientId(Long productId, Long clientId);

    @Query("SELECT sc FROM ShopCart sc WHERE sc.client.id = :clientId AND sc.savedProduct = true")
    List<ShopCart> findSavedProductByClient_Id(Long clientId);
}
