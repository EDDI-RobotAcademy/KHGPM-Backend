package com.example.demo.board.controller;

import com.example.demo.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class BoardController {

    final private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
}
