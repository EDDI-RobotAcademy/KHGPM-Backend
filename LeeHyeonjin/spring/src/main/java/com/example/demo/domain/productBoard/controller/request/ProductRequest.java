package com.example.demo.domain.productBoard.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ProductRequest {
    private final String title;
    private final String writer;
    private final Long price;
    private final String delivery;
    private final String content;



}
