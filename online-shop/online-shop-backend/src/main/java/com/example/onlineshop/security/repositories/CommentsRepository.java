package com.example.onlineshop.security.repositories;

import com.example.onlineshop.security.models.Comments;
import com.example.onlineshop.security.models.ShopCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @Query("SELECT com FROM Comments com WHERE com.client.id = :clientId")
    Optional<Comments> findCommentsByClientId(Long clientId);

    @Query("SELECT com FROM Comments com WHERE com.product.id = :productId")
    List<Comments> findCommentsByProduct_Id(Long productId);
}
