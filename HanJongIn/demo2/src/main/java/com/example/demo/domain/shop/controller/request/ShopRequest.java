package com.example.demo.domain.shop.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class ShopRequest {

    private String name;
    private String description;
    private Integer price;
    private List<MultipartFile> fileList;

    public ShopRequest(String name, String description, Integer price, List<MultipartFile> fileList) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.fileList = fileList;
    }
}

