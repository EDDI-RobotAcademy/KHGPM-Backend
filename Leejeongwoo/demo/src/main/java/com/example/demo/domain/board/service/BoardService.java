package com.example.demo.domain.board.service;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;

import java.util.List;

public interface BoardService {
    //현재 register는 void 로써 리턴이 없는 자료형을 뜻한다
//    public void register(BoardRequest boardRequest);

    //얘는 보드 id반환 하려고 Long형으로 반환
//    public Long register(BoardRequest boardRequest);

    public Board register(BoardRequest boardRequest);

    List<Board> list();
    Board read(Long boardId);
    void remove(Long boardId);
    Board modify(Long boardId, BoardRequest boardRequest);
    Long getCount();
    Long getLastEntityId();
}