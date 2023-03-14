package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class BoardController {

    // @RestController = @Controller + @ResponseBody


    final private BoardService boardService;
    /*
    @PostMapping("/register")
    public void boardRegister (@RequestBody BoardRequest boardRequest) {
        log.info("boardRegister()");

        boardService.register(boardRequest);
    }
     */
    @PostMapping("/register")
    public Board boardRegister (@RequestBody BoardRequest boardRequest) {
        log.info("boardRegister()");

        // @RequestBody 요청이 온 데이터 (JSON or XML)을 Class or model로 매핑하기 위한 Annotation
        // Http POST 요청에 대해 request body에 있는 request message에서 값을 얻어와 매핑한다.
        // RequestData를 바로 Model or Class로 매핑한다.

        return boardService.register(boardRequest);
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
    public void boardRemove(@PathVariable("boardId") Long boardId) {
        // @PathVariable : Http 요청에 대해 mapping 되는 request parameter 값이 자동으로 binding
        // URI에서 각 구분자에 들어오는 값을 처리해야 할 때 사용한다.
        // REST API에서 값을 호출할때 주로 많이 사용한다.
        log.info("boardRemove()");

        boardService.remove(boardId);
    }

    @PutMapping("/{boardId}")
    public Board boardModify(@PathVariable("boardId") Long boardId,
                             @RequestBody BoardRequest boardRequest) {

        log.info("boardModify(): " + boardRequest + "id: " + boardId);

        return boardService.modify(boardId, boardRequest);
    }
}
