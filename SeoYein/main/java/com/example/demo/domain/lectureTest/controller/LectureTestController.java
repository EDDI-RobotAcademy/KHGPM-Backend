package com.example.demo.domain.lectureTest.controller;


import com.example.demo.domain.lectureTest.entity.TestMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/spring-2nd")
public class LectureTestController {

    @GetMapping("/first")
    public String helloSpring () {
        log.info("helloSpring(): 이거 왜 안돼?");

        return "Hello Spring";
    }

    @GetMapping("/second")
    public String helloSpring2 () {
        return "Hello Test";
    }

    @GetMapping("/third")
    public TestMember returnEntityTest () {
        TestMember testMember =
                new TestMember(1L, "hi", 7L);
                                // 그냥 1L, "hi", 7L 이렇게만 쓰면 알아서 불러와줌! 짱이다
        return testMember;
    }
}
