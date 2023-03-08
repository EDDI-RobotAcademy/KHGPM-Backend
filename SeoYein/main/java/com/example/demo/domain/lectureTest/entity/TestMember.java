package com.example.demo.domain.lectureTest.entity;

import lombok.Data;

@Data // Data 어노테이션은 getter, setter 역할을 포함한다.
public class TestMember {

    final private Long memberId;

    final private String name;

    final private Long age;
}
