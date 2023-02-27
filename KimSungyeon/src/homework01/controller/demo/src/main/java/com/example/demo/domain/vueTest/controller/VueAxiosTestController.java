package com.example.demo.domain.vueTest.controller;
import com.example.demo.domain.vueTest.controller.request.VueRequestTestData;
import com.example.demo.domain.vueTest.entity.BoardGameDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/first")
@CrossOrigin(origins = "http://localhost:8080",allowedHeaders = "*")
public class VueAxiosTestController {


    @PostMapping("/receive-test")
    public void receiveTest(@RequestBody VueRequestTestData vueRequestTestData){
        log.info("데이터 확인" + vueRequestTestData);
    }
}
