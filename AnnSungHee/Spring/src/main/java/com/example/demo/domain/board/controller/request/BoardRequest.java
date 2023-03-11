package com.example.demo.domain.board.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 해당 클래스에 멤버변수에 대한 getter 메서드를 자동으로 생성
@RequiredArgsConstructor // 모든 final 멤버변수를 인수로 받는 생성자를 생성합니다. 이는 변경할 수 없는 객체를 생성하는 데 유용
public class BoardRequest {

    final private String title;
    final private String writer;
    final private String content;
}