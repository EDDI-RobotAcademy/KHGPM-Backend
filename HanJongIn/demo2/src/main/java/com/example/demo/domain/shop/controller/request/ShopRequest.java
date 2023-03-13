package com.example.demo.domain.shop.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
//@Setter
@NoArgsConstructor
public class ShopRequest {

    private String name;
    private String description;
    private Integer price;

    public ShopRequest(String name, String description, Integer price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
