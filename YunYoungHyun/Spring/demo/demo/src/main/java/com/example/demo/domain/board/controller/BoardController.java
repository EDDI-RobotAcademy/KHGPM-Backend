package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Board boardRegister(@RequestBody BoardRequest boardRequest) {
        log.info("boardRegister()");

        Board board  = boardService.register(boardRequest);
        System.out.println("글 작성 후 boardID : "+ board.getBoardId());
        return board;
    }

    @GetMapping("/list")
    public List<Board> boardList() {
        log.info("boardList()");

        return boardService.list();
    }

    @GetMapping("/{boardId}")
    public Board boardRead(@PathVariable("boardId") Long boardId) {
//        log.info("boardRead()");
        System.out.println("boardId: "+ boardId);
        return boardService.read(boardId);
    }

    @DeleteMapping("/{boardId}")
    public void boardRemove(@PathVariable("boardId") Long boardId) {
        log.info("boardModify()");

        boardService.remove(boardId);
    }

    @PutMapping("/{boardId}")
    public Board boardModify(@PathVariable("boardId") Long boardId, @RequestBody BoardRequest boardRequest) {
        log.info("boardModify()");

        return boardService.modify(boardId, boardRequest);
    }
}