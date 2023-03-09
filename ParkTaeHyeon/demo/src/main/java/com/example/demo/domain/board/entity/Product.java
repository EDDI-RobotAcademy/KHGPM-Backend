package com.example.demo.domain.board.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(length = 128, nullable = false)
    private String name;

    @Column(length = 128, nullable = false)
    private String price;

    @Lob
    private String content;
}
