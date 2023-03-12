package com.example.demo.domain.product.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(length = 32, nullable = false)
    private int price;

    @Lob
    private String detail;

    @CreationTimestamp
    private Date regDate;
}
