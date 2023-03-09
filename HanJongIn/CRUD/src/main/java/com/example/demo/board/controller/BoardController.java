package com.example.demo.board.controller;

import com.example.demo.board.controller.request.BoardRequest;
import com.example.demo.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class BoardController {

    final private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/register")
    public void boardRegister(@RequestBody BoardRequest boardRequest) {
        log.info("boardRegister");

        boardService.register(boardRequest);
    }
}
