package com.example.demo.domain.product.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class RequestProductInfo {
    final private String title;
    final private Integer price;
    final private String detail;
}
