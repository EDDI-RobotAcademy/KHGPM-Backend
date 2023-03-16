package com.example.demo.domain.product.controler.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductRequest {

    final private String productName;
    final private String kategorie;
    final private String content;
    final private Integer price;
    final private String brand;
}