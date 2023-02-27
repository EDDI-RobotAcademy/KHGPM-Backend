package com.example.demo.domain.lectureTest.controller;

import com.example.demo.domain.lectureTest.entity.TestMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/spring-2th")
public class LectureTestController {

    @GetMapping("/first")
    public String helloSpring() {
        log.info("helloSpring(): 이거 왜");
        return "Sung yeon Test1";
    }

    @GetMapping("/second")
    public String TestSpring() {
        return "Sung yeon Test2";
    }

    @GetMapping("/second2")
    public String TestSpring2() {
        return "Hello Spring2";
    }

    @GetMapping("/third")
    public TestMember returnEntityTest() {
        TestMember t = new TestMember(2L, "uesr1", 7L);
        System.out.println(t.toString());
        return t;
    }
}
