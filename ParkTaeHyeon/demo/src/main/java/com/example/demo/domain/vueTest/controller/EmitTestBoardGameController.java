package com.example.demo.domain.vueTest.controller;

import com.example.demo.domain.vueTest.controller.request.VueRequestTestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/first")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class EmitTestBoardGameController {

    @PostMapping("/winner-test")
    public void receiveTest (@RequestBody VueRequestTestData.EmitTestBoardGameData emitTestBoardGameData) {
        log.info("오목 게임 승자 확인 : " + emitTestBoardGameData);
    }
}
