package com.example.demo.domain.product.controler.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestProductInfo {
    final private String productName;
    final private String kategorie;
    final private String content;
    final private String brand;
    final private Integer price;

}