package com.example.demo.domain.member.service;

import com.example.demo.domain.member.entity.Authentication;
import com.example.demo.domain.member.entity.BasicAuthentication;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.AuthenticationRepository;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.service.request.MemberLoginRequest;
import com.example.demo.domain.member.service.request.MemberRegisterRequest;
import com.example.demo.domain.security.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;
    final private AuthenticationRepository authenticationRepository;
    final private RedisService redisService;

    @Override
    public Boolean emailValidation(String email) {
        Optional<Member> maybeMember = memberRepository.findByEmail(email);

        // 중복 발생.
        if (maybeMember.isPresent()) {
            return false;
        }

        // 중복 아님. 유효함.
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
        Optional<Member> maybeMember = memberRepository.findByEmail(memberLoginRequest.getEmail());
        System.out.println("서비스에서 보는 maybeMember: "+ maybeMember);
        System.out.println("서비스에서 보는 maybeMember.isPresent(): "+ maybeMember.isPresent());

        if(maybeMember.isPresent()) {
            Member member = maybeMember.get();

            System.out.println("서비스에서 보는 getPassword(): "+ memberLoginRequest.getPassword());
            System.out.println("서비스에서 보는 isRightPassword(): "+ member.isRightPassword(memberLoginRequest.getPassword()));

            if(!member.isRightPassword(memberLoginRequest.getPassword())) {
                throw new RuntimeException("이메일 또는 비밀번호가 잘못되었습니다!");
            }

            UUID userToken = UUID.randomUUID();

            // redis 처리 필요!!!
            redisService.deleteByKey(userToken.toString());
            redisService.setKeyAndValue(userToken.toString(), member.getId());

            return userToken.toString();
        }

        throw new RuntimeException("가입된 사용자가 아닙니다!");
    }

}