package com.example.demo.domain.board.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class BoardRequest {

    private String title;
    private String writer;
    private String content;


}