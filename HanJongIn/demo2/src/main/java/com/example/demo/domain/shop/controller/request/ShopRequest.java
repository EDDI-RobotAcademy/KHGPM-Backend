package com.example.demo.domain.shop.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class ShopRequest {

    private String name;
    private String description;
    private Integer price;
}
