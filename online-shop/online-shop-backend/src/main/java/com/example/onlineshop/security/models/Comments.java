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
public class Comments {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User client;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

}
