package com.example.demo.domain.product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class ProductImgs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImgId;

    private String imgPath;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    public ProductImgs (String imgPath) { this.imgPath = imgPath; }

}
