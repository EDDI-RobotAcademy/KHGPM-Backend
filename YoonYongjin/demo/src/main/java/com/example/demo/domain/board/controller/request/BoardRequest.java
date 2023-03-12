package com.example.demo.domain.board.controller.request;

import lombok.Getter;

@Getter
public class BoardRequest {


    private String title;
    private String writer;
    private String content;
}