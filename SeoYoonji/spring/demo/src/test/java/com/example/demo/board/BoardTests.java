package com.example.demo.board;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardTests {
    @Autowired
    private BoardService boardService;
    @Test
    public void 게시물_저장_테스트() {
        BoardRequest boardRequest = new BoardRequest("test", "test", "test");
        boardService.register(boardRequest);
    }

    @Test
    public void 게시물_리스트_테스트() {
        System.out.println(boardService.list());
    }

    @Test
    public void 게시물_읽기_테스트() {
       Board board = boardService.read(5L);
        System.out.println(board);
    }

    @Test
    public void 게시물_수정_테스트() {
        Board board = boardService.modify(4L, new BoardRequest(
                "게시글변경", "테스트", "잘되나"));

        System.out.println(board);
    }

    @Test
    public void 게시물_삭제_테스트 () {
        boardService.remove(3L);
        boardService.read(3L);
    }

    @Test
    public void 현재_게시물_개수 () {
        System.out.println(boardService.getCount());
    }
}
