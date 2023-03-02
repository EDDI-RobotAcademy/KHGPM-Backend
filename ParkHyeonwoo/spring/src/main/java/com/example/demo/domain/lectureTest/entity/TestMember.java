package com.example.demo.domain.lectureTest.entity;

import lombok.Data;

@Data // getter, setter, toString 역할 한번에 해줌
public class TestMember {

    final private Long memberId;
    final private String name;
    final private Long age;
}
