package com.example.demo.domain.vueTest.controller;

import com.example.demo.domain.vueTest.controller.request.EmitRequestTestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/first")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class EmitAxiosTestController {

    @PostMapping("/emit-receive-test")
    public void emitReceiveTest (@RequestBody EmitRequestTestData emitRequestTestData) {
        log.info("게임 승자 확인: " + emitRequestTestData);
    }
}
