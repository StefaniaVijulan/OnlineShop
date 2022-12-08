package com.example.onlineshop.security.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
public class OrderProduct {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private BillingData billingData;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User client;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name="finalized")
    private Boolean finalized;

    @Column(name="orderDate")
    private Date orderDate;

}
