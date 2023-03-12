package com.example.demo.domain.lectureTest.controller;


import com.example.demo.domain.lectureTest.entity.TestMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j    // 로그 역할(Lombok 일종)
@RestController   //Controller임을 명시 (모든 데이터를 json으로 처리함을 안내)
@RequestMapping("/spring-2nd")     //localhost:7777/spring-2th 로 연결
//RequestMapping : 클래스 단계에서 사용함
public class LectureTestController {

    @GetMapping("/first")   //localhost:7777/spring-2th/first 로 연결
    //GetMapping : 메서드 단계에서 사용함
    public String helloSpring(){
        log.info("hellospring(): 웨안댐");
        //Slf4j로 인해 log.info 가능
        return "Hello Spring";
    }

    @GetMapping("/second")    //GetMapping("/first") 와 동일함
    public String helloSpring2() {
        return "Hello Test";
    }

    @GetMapping("/third")
    public TestMember returnEntityTest() {
        TestMember testMember= new TestMember(1L, "hi", 7L);
        return testMember;
    }
}
