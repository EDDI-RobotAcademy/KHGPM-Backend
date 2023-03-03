package com.example.demo.domain.vueTest.controller;

import com.example.demo.domain.vueTest.controller.request.VueRequestTestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/first")
@CrossOrigin(origins="http://localhost:8080", allowedHeaders="*") //allowHeaders = POST, GET, 전부(*) 등등 골라서 받을 수 있는 기능
//서로 다른 도메인에서 리소스를 공유하는 방식의 어노테이션
public class VueAxiosTestController {

    @PostMapping("/receive-test")
    public void receiveTest(@RequestBody VueRequestTestData vueRequestTestData) {
                        //JSON으로 처리하기위해 RequestBody로 처리
        log.info("요청된 데이터 정보 확인 :" + vueRequestTestData);


    }


}
