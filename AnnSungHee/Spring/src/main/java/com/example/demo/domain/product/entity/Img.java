package com.example.demo.domain.product.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    @Column(length = 128, nullable = false)
    private String imgName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
}
