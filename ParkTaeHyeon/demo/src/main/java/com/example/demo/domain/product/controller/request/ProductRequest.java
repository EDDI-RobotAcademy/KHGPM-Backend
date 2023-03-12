package com.example.demo.domain.product.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductRequest {

    final private String name;
    final private String price;
    final private String content;
}