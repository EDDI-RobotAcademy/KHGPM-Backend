package com.example.demo.domain.product.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productNo;

    @Column(length = 128, nullable = false)
    private String name;

    @Column(length = 32, nullable = false)
    private Long price;

    @Column(length = 32, nullable = false)
    private String writer;

    @Lob
    private String content;

    @Column(length = 32, nullable = false)
    private Long views;

}