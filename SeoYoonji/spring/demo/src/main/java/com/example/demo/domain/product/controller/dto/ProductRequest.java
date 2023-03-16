package com.example.demo.domain.product.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductRequest {

    final private String title;
    final private Integer price;
    final private String detail;

}
