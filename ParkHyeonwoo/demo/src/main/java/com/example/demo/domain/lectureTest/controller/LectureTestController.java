package com.example.demo.domain.lectureTest.controller;

import com.example.demo.domain.lectureTest.entity.TestMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // 로그 위함, 롬북의 일종
@RestController
@RequestMapping("/spring-2th") // 전체 도메인 틀 매핑
public class LectureTestController {

    @GetMapping("/first") // 틀 뒤에 들어갈 도메인 매핑
    public String helloSpring() {
        log.info("helloSpring(): 로그");

        return "Hello Spring";
    }

    @GetMapping("/second")
    public String helloSpring2 () {
        return "Hello Test";
    }

    @GetMapping("/third")
    public TestMember returnEntityTest() {
        TestMember testMember = new TestMember(1L, "hi", 7L);

        return testMember;
    }
}
