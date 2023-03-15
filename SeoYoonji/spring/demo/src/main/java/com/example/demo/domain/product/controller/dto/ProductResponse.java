package com.example.demo.domain.product.controller.dto;

import com.example.demo.domain.product.entity.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public class ProductResponse {

    private Long productId;
    private String title;
    private Integer price;
    private String detail;

    public ProductResponse(Product product) {
        this.productId = product.getProductId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.detail = product.getDetail();
    }
}
