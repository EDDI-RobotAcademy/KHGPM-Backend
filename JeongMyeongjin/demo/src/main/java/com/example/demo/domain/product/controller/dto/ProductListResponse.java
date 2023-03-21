package com.example.demo.domain.product.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ProductListResponse {

    //상품 리스트를 응답하기 위한 객체
    final private Long productId;
    final private String productName;
    final private String writer;
    final private Date regDate;
}