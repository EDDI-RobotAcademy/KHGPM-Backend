package com.example.demo.domain.vueTest.controller;
import com.example.demo.domain.vueTest.entity.BoardGameDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vue/game")
@CrossOrigin(origins = "http://localhost:8080",allowedHeaders = "*")
public class BoardGameController {

    @PostMapping("/winner")
    public void winner(@RequestBody BoardGameDTO gameDTO){
        log.info("데이터 확인" + gameDTO);
    }
}

