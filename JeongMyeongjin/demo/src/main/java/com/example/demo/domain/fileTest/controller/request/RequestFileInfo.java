package com.example.demo.domain.fileTest.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestFileInfo {

    final private Integer price;
    final private String test;
}