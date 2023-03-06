package com.example.demo.domain.board.service;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;

import java.util.List;

public interface BoardService {
    public void register(BoardRequest boardRequest);

    List<Board> list();
}
