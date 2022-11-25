package com.example.onlineshop.security.models;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "sketching")
    private String sketching;

    @Column(name = "finalProduct")
    private String finalProduct;

    @Column(name = "productName")
    private String productName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

}
