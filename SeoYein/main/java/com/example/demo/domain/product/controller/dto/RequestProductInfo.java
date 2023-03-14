package com.example.demo.domain.product.controller.dto;

import com.example.demo.domain.product.entity.ImageResource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestProductInfo {
    final private String productName;
    final private String writer;
    final private String content;
    final private Integer price;
}
