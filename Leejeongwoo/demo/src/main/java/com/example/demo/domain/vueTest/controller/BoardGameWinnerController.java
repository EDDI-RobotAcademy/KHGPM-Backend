package com.example.demo.domain.vueTest.controller;

import com.example.demo.domain.vueTest.controller.request.BoardGameWinnerData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/second")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class BoardGameWinnerController {

    @PostMapping("/receive-winner")
    public void receiveWinner (@RequestBody BoardGameWinnerData boardGameWinnerData) {
        log.info("요청된 데이터 정보 확인: " + boardGameWinnerData);
        System.out.println("오목게임의 승리자는 " + boardGameWinnerData.getReceivedWinner() + "입니다.");
    }
}