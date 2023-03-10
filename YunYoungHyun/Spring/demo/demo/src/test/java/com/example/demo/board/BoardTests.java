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
        BoardRequest boardRequest = new BoardRequest("이거", "정말", "되냐");
        boardService.register(boardRequest);
    }

    @Test
    public void 게시물_리스트_테스트() {
        System.out.println("게시물_리스트_테스트: "+ boardService.list());
    }

    @Test
    public void 게시물_읽기_테스트() {
        Board board = boardService.read(4L);
        System.out.println("게시물_읽기_테스트: "+ board);
    }

    @Test
    public void 게시물_수정_테스트() {
        Board board = boardService.modify(2L, new BoardRequest("뭐야", "왜", "변경하니 ?"));
        System.out.println("게시물_수정_테스트: "+ board);
    }

    @Test
    public void 게시물_삭제_테스트() {
        boardService.remove(2L);
        System.out.println("게시물_삭제_테스트: "+ boardService.read(2L));
    }

    @Test
    public void 현재_게시물_개수() {
        System.out.println("현재_게시물_개수: "+ boardService.getCount());
    }

    @Test
    public void 마지막_엔티티_id번호(){
        System.out.println(boardService.getLastEntityId());
    }

    @Test
    public void 게시물_구동_전체_테스트() {
        BoardRequest boardRequest = new BoardRequest("이거", "정말", "되냐");
        Board board = boardService.register(boardRequest);

        System.out.println("초기 등록: "+ boardService.read(board.getBoardId()));

        boardService.modify(board.getBoardId(), new BoardRequest("뭐야", "왜", "변경하니 ?"));
        System.out.println("수정 후: "+ boardService.read(board.getBoardId()));

        boardService.remove(board.getBoardId());
        System.out.println("삭제 후: "+ boardService.read(board.getBoardId()));
    }

}