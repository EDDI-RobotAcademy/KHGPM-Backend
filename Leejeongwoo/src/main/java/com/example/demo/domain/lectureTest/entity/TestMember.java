package com.example.demo.domain.lectureTest.entity;

import lombok.Data;

@Data // 데이터 어노테이션가능
public class TestMember {
    final private Long memberId;

    final private String name;

    final private Long age;
}
