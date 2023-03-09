package com.example.demo.domain.product.controler.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductRequest {

    final private String productKategorie;
    final private String productName;
    final private String writer;
    final private String productContent;
    final private Integer productPrice;
    final private String productBrand;
}