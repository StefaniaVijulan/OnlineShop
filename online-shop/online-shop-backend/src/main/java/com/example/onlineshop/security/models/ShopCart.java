package com.example.onlineshop.security.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
public class ShopCart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User client;

    @Column(name = "savedProduct")
    private Boolean savedProduct;

    @Column(name = "quantity")
    private Integer quantity;

}
