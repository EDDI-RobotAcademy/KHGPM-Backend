package com.example.demo.domain.product.controller.dto;

import com.example.demo.domain.product.entity.ImageResource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ProductListResponse {
    final private Long productId;
    final private String productName;
    final private String writer;
    final private Date regDate;
}
