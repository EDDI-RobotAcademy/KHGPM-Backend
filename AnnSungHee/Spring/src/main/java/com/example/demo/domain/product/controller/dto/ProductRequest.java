package com.example.demo.domain.product.controller.dto;

        import lombok.Getter;
        import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductRequest {

    final private String productName;
    final private String writer;
    final private String content;
    final private Integer price;
}
