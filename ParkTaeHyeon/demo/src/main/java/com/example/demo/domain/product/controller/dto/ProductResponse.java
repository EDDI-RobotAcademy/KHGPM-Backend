package com.example.demo.domain.product.controller.dto;

import com.example.demo.domain.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RequiredArgsConstructor
public class ProductResponse {
    private Long productId;
    private String productName;
    private String writer;
    private Date regDate;


    public ProductResponse(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.writer = product.getWriter();
        this.regDate = product.getRegDate();
    }
}
