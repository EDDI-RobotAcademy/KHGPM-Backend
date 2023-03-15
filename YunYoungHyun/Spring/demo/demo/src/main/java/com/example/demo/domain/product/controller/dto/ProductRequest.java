package com.example.demo.domain.product.controller.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Lob;

@Getter
public class ProductRequest {

    private String name;
    private Long price;
    private String writer;
    private String content;
    private String imageName;

}