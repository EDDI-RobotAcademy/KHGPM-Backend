package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j //로그 남기기
@RestController  //@Controller + @ResponseBody -> 단순히 객체만을 반환. JSON 형식으로 객체 데이터를 전송한다.
@RequestMapping("/board") //클라이언트 요청 처리 url 맵핑
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class BoardController {

    final private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/register")
    public void boardRegister (@RequestBody BoardRequest boardRequest) {
        //@RequestBody 가 붙은 파라미터에는 http요청의 본문 body가 그대로 전달됨
        log.info("boardRegister()");

        boardService.register(boardRequest);
    }
}
