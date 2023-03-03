package com.example.demo.domain.vueTest.controller;

import com.example.demo.domain.vueTest.controller.request.EmitRequestTestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/Emit")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class EmitAxiosTestController {

    @PostMapping("/receive-test")
    public void receiveTest (@RequestBody EmitRequestTestData EmitRequestTestData) {

        log.info("승패 확인: " + EmitRequestTestData);
    }
}
