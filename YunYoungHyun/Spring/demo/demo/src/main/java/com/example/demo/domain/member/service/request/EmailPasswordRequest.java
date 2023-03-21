package com.example.demo.domain.member.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class EmailPasswordRequest {

    private final String email;
    private final String password;
}
