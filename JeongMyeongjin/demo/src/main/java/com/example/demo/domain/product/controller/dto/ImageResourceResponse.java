package com.example.demo.domain.product.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageResourceResponse {

    // 이미지 경로를 응답하기 위한 객체
    private String imageResourcePath;
}