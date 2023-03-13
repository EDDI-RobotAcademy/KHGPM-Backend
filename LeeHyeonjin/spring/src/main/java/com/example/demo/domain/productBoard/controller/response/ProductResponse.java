package com.example.demo.domain.productBoard.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ProductResponse {
    private final Long productId;
    private final String title;
    private final String writer;
    private final Long price;
    private final String delivery;
    private final Date regDate;

}
