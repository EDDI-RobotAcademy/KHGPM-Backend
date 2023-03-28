package com.example.demo.board.service;

import com.example.demo.board.controller.request.BoardRequest;
import com.example.demo.board.entity.Board;

import java.util.List;

public interface BoardService {
    //public void register(BoardRequest boardRequest);
    public Board register(BoardRequest boardRequest);

    List<Board> list();

    Board read(Long boardId);

    void remove(Long boardId);

    Board modify(Long boardId, BoardRequest boardRequest);

    List<Board> bigMisstake(Long boardId, BoardRequest boardRequest);

    Long getCount();

    Long getLastEntityId();
}
