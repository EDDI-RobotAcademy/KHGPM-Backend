package com.example.demo.domain.vueTest.controller;

import com.example.demo.domain.vueTest.controller.request.EmitBoardGameData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/board")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class EmitBoardGameController {

    @PostMapping("/game/winner")
    public void showWinner(@RequestBody EmitBoardGameData emitBoardGameData) {
        System.out.println("오목 게임의 승자는~~ "+ emitBoardGameData.getWinner() +" 님 입니다!");
    }

}