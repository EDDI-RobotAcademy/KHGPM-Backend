package com.example.demo.board;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardTest {
    @Autowired
    private BoardService boardService;

    @Test
    public void 게시물_저장_테스트() {
        BoardRequest boardRequest =
                new BoardRequest("이거", "정말", "되냐");

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
        Board board = boardService.modify(5L, new BoardRequest(
                "타이틀 수정", "작성자 수정", "내용 수정"
        ));

        System.out.println(board);
    }

    @Test
    public void 게시물_삭제_테스트() {
        boardService.remove(5L);

        boardService.read(5L);
    }

    @Test
    public void 현재_게시물의_개수 () {
        System.out.println(boardService.getCount());
    }

    @Test
    public void 마지막_엔티티_id번호 () {
        System.out.println(boardService.getLastEntityId());
    }

    @Test
    public void 게시판_구동_전체_테스트 () {
        BoardRequest boardRequest =
                new BoardRequest("타이틀", "작성자", "내용");
        Board board = boardService.register(boardRequest);
        Long boardId = board.getBoardId();

        System.out.println("초기 등록: " + boardService.read(boardId));

        boardService.modify(boardId, new BoardRequest(
                "타이틀 수정", "작성자 수정", "내용 수정"));

        System.out.println("수정 후: " + boardService.read(boardId));

        boardService.remove(boardId);

        System.out.println("삭제 후: " + boardService.read(boardId));
    }




}
