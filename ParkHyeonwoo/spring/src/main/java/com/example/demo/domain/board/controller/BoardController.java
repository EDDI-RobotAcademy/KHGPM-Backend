package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 콘솔이나 로그 파일에 메시지를 기록(Lombok)
@RestController // JSON 형태로 객체 데이터 반환
@RequiredArgsConstructor // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 Lombok 어노테이션
@RequestMapping("/board") // 서버 URL 과 매핑
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*") // 다른 도메인에서 접근을 허용해주는 매커니즘, 모든 헤더 허용
public class BoardController {

    final private BoardService boardService;

    // @RequestBody : form 형태로 날아오는 정보들과 매핑되게끔 하는 역할
    @PostMapping("/register")
    public Board boardRegister (@RequestBody BoardRequest boardRequest) {
        log.info("boardRegister()");

        return boardService.register(boardRequest);

    }

    @GetMapping("/list")
    public List<Board> boardList() {
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