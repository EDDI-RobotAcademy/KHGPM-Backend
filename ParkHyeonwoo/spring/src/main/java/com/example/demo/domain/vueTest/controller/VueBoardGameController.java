package com.example.demo.domain.vueTest.controller;

import com.example.demo.domain.vueTest.controller.request.VueRequestGameData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/second") // 주소 매핑
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*") // allowedHeaders = ?
public class VueBoardGameController {

    @PostMapping("/receive-test")
    public void receiveTest(@RequestBody VueRequestGameData VueRequestGameData) {
        log.info("요청된 데이터 정보 확인: " + VueRequestGameData);
    }
}
