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
    public void boardRegister (@RequestBody BoardRequest boardRequest) {
        log.info("boardRegister()");
        // 요청 처리함, 어떤 요청이 들어왔는지 알 수 있음 , 받이오기만하기 때문에 getter만 함
        boardService.register(boardRequest);
    }
    @GetMapping("/list")
    public List<Board> boardList () {
        log.info("boardList()");

        return boardService.list();
    }

    @GetMapping("/{boardId}")
    public Board boardRead(@PathVariable("boardId") Long boardId) {
        log.info("boardRead()");

        return boardService.read(boardId);
    }

    @DeleteMapping("/{boardId}")
    public void boardRemove(@PathVariable("boardId") Long boardId){
        log.info("boardRemove()");
        boardService.remove(boardId);
    }

    @PutMapping("/{boardId}")
    public void boardModify(@PathVariable("boardId") Long boardId,
                            @RequestBody BoardRequest boardRequest){
        boardService.modify(boardId, boardRequest);
    }

}