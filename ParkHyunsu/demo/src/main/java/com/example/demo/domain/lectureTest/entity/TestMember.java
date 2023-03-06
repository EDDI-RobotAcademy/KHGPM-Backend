package com.example.demo.domain.lectureTest.entity;


import lombok.Data;

@Data
//생성자와 Getter Setter 를 한번에 처리함
public class TestMember {

        final private Long memberId;
        final private String name;
        final private Long age;
}
