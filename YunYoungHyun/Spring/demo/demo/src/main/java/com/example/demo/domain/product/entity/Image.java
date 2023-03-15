package com.example.demo.domain.product.entity;

import com.example.demo.domain.product.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String imagePath;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_no")
    private Product product;

    public Image(String imagePath) {
        this.imagePath = imagePath;
    }

}