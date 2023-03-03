package com.example.demo.domain.hwVueTest.controller;

import com.example.demo.domain.hwVueTest.controller.request.HwVueRequestTestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/second")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")


public class HwVueAxiosTestController {
    @PostMapping("/receive-winner")
    public void receiveTest(@RequestBody HwVueRequestTestData hwVueRequestTestData) {
        log.info("요청된 데이터 정보 확인: " + hwVueRequestTestData);
    }
}