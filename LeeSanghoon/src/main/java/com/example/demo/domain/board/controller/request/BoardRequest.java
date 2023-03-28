package com.example.demo.domain.board.controller.request;

import com.example.demo.domain.board.entity.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardRequest {

    final private String title;
    final private String writer;
    final private String content;

    public Board toBoard () {
        return new Board(title, writer, content);
    }
}
