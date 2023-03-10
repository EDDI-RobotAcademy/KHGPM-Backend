package com.example.demo.domain.board.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// front 에서 전달받는것들
@Getter
@RequiredArgsConstructor
public class BoardRequest {

    final private String title;
    final private String writer;
    final private String content;

}