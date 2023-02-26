package com.example.demo.domain.vueTest.controller;

import com.example.demo.domain.vueTest.controller.request.VueRequestEmitData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/first")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class VueAxiosWinnerTestController {
    @PostMapping("/winner-test")
    public void winnerTest(@RequestBody VueRequestEmitData vueRequestEmitData) {
        log.info("요청된 데이터 정보 확인: " + vueRequestEmitData);
    }
}
