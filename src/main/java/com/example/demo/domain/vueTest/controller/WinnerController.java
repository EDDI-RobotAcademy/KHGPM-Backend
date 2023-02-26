package com.example.demo.domain.vueTest.controller;

import com.example.demo.domain.vueTest.controller.request.WinnerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/first")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class WinnerController {

    @PostMapping("/winner")
    public void winner (@RequestBody WinnerRequest winnerRequest) {
        log.info("승자는 누구인가: " + winnerRequest.getWinner());
    }
}
