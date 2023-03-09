package com.example.demo.domain.vueTest.controller;

import com.example.demo.domain.vueTest.controller.request.VueRequestTestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/first")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*") //http동작 전부 허용
public class VueAxiosTestController {

    @PostMapping("/receive-test") //vue에서 post로 보냈으니 @PostMapping, json 형식으로 데이터 처리를 위해 @RequestBody 사용
    public void receiveTest (@RequestBody VueRequestTestData vueRequestTestData) {
        log.info("요청된 데이터 정보 확인: " + vueRequestTestData);
    }
}
