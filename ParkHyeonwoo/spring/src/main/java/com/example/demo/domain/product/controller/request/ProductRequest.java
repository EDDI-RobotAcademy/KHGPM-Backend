package com.example.demo.domain.product.controller.request;

import lombok.Getter;

@Getter
public class ProductRequest {

    private String productName;
    private String writer;
    private String content;
    private Integer price;
}
