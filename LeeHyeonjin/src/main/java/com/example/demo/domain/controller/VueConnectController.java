package com.example.demo.domain.controller;


import com.example.demo.domain.controller.request.VueRequestName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/connect")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class VueConnectController {
    @PostMapping("/receive-name")
    public void receiveTest (@RequestBody VueRequestName reqName) {
        log.info("승리자 이름: " + reqName);
    }
}



