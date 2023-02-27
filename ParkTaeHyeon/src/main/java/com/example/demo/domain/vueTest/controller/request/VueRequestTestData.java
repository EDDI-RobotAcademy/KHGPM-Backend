package com.example.demo.domain.vueTest.controller.request;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class VueRequestTestData {

    private String memberName;
    private String major;

    @ToString
    @Getter
    public static class EmitTestBoardGameData {
        private String winner;
    }
}