package com.example.demo.domain.lectureTest.entity;

import lombok.Data;

// 생성자와 getter, setter 안 적어도 된다.
@Data
public class TestMember {

    final private Long memberId;
    final private String name;
    final private Long age;

}
