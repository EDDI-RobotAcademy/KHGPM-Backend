package com.example.demo.domain.member.service;

import com.example.demo.domain.member.entity.Authentication;
import com.example.demo.domain.member.entity.BasicAuthentication;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.AuthenticationRepository;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.service.request.MemberLoginRequest;
import com.example.demo.domain.member.service.request.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;

    final private AuthenticationRepository authenticationRepository;

    @Override
    public Boolean emailValidation(String email) {
        Optional<Member> maybeMember = memberRepository.findByEmail(email);

        if (maybeMember.isPresent()) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean signUp(MemberRegisterRequest memberRegisterRequest) {
        final Member member = memberRegisterRequest.toMember();
        memberRepository.save(member);

        final BasicAuthentication authentication = new BasicAuthentication(
                member,
                Authentication.BASIC_AUTH,
                memberRegisterRequest.getPassword()
        );

        authenticationRepository.save(authentication);

        return true;
    }

    @Override
    public String signIn(MemberLoginRequest memberLoginRequest) {
        Optional<Member> maybeMember =
                memberRepository.findByEmail(memberLoginRequest.getEmail());

        System.out.println("loginRequest: " + memberLoginRequest);

        if (maybeMember.isPresent()) {
            Member member = maybeMember.get();

            if (!member.isRightPassword(memberLoginRequest.getPassword())) {
                throw new RuntimeException("이메일 및 비밀번호 입력이 잘못되었습니다!");
            }

            UUID userToken = UUID.randomUUID();

            // redis 처리 필요

            return userToken.toString();
        }

        throw new RuntimeException("가입된 사용자가 아닙니다!");
    }
}