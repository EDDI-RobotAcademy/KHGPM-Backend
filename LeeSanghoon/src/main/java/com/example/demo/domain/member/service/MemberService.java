package com.example.demo.domain.member.service;

import com.example.demo.domain.member.service.request.MemberLoginRequest;
import com.example.demo.domain.member.service.request.MemberRegisterRequest;

public interface MemberService {
    Boolean emailValidation(String email);
    Boolean signUp(MemberRegisterRequest memberRegisterRequest);
    String signIn(MemberLoginRequest memberLoginRequest);
}
