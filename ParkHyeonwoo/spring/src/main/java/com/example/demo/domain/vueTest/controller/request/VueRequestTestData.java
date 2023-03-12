package com.example.demo.domain.vueTest.controller.request;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class VueRequestTestData {

    // vue 에서의 변수명과 이름을 맞춰주어야 한다.
    private String memberName;
    private String major;
}
