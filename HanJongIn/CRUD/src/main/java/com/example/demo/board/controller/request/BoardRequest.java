package com.example.demo.board.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardRequest {

    final private String title;
    final private String content;
    final private String writer;

}
