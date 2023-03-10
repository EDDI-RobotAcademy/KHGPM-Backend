package com.example.demo.board;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// 테스트는 보통 도메인 단위로 하며 도메인의 Service(기능) 를 기준으로 테스트한다.
@SpringBootTest
public class BoardTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void 게시물_저장_테스트() {
        BoardRequest boardRequest = new BoardRequest("이거", "정말", "되냐");

        boardService.register(boardRequest);
    }

    @Test
    public void 게시물_리스트_테스트() {
        System.out.println(boardService.list());
    }

    @Test
    public void 게시물_읽기_테스트() {
        Board board = boardService.read(15L);
        System.out.println(board);
    }

    @Test
    public void 게시물_수정_테스트() {
        Board board = boardService.modify(15L, new BoardRequest("뭐야", "왜", "변경함?"));

        System.out.println(board);
    }

    @Test
    public void 게시물_삭제_리스트() {
        boardService.remove(15L);
        boardService.read(15L);
    }

    @Test
    public void 현재_게시물의_개수 () {
        System.out.println(boardService.getCount());
    }

    @Test
    public void 게시판_구동_전체_테스트() {
        BoardRequest boardRequest = new BoardRequest("이거", "정말", "되냐");

        boardService.register(boardRequest);
        Long lastBoardId = boardService.getLastEntityId();

        System.out.println("초기 등록: " + boardService.read(lastBoardId));

        boardService.modify(lastBoardId, new BoardRequest("뭐야", "왜", "변경함?"));

        System.out.println("수정 후: " + boardService.read(lastBoardId));

        boardService.remove(lastBoardId);

        System.out.println("삭제 후: " + boardService.read(lastBoardId));
    }
}
