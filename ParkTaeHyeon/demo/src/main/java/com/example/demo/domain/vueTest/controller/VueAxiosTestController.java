package com.example.demo.domain.vueTest.controller;

import com.example.demo.domain.vueTest.controller.request.VueRequestTestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/first")
// 실제 Vue port인 8080으로 오는 것인지 확인하기 위함
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class VueAxiosTestController {

    @PostMapping("/receive-test")
    public void receiveTest (@RequestBody VueRequestTestData vueRequestTestData) {
        //RequestBody : Json으로 처리하기 위함
        log.info("요청된 데이터 정보 확인: " + vueRequestTestData);
    }
}