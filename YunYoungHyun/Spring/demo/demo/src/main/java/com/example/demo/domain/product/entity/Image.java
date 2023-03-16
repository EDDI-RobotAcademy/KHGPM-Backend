package com.example.demo.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String imagePath;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_no")
    @JsonBackReference
    private Product product;

    public Image(String imagePath) {
        this.imagePath = imagePath;
    }

}