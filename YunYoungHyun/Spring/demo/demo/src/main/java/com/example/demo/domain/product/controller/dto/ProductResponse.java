package com.example.demo.domain.product.controller.dto;

import com.example.demo.domain.product.entity.Product;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductResponse {

    private Long productNo;
    private String name;
    private Long price;
    private String writer;
    private String content;

    public ProductResponse(Long productNo, String name, Long price, String writer, String content) {
        this.productNo = productNo;
        this.name = name;
        this.price = price;
        this.writer = writer;
        this.content = content;
    }

    public ProductResponse(Product product) {
        this.productNo = product.getProductNo();
        this.name = product.getName();
        this.price = product.getPrice();
        this.writer = product.getWriter();
        this.content = product.getContent();
    }

}