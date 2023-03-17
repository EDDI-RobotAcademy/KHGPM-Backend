package com.example.demo.domain.member.service;

import com.example.demo.domain.member.entity.Authentication;
import com.example.demo.domain.member.entity.BasicAuthentication;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.AuthenticationRepository;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.service.request.EmailMatchRequest;
import com.example.demo.domain.member.service.request.EmailPasswordRequest;
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
        System.out.println("maybeMember.isPresent(): " + maybeMember.isPresent());

        if (maybeMember.isPresent()) {
            Member member = maybeMember.get();

            System.out.println("사용자가 입력한 비번: " + memberLoginRequest.getPassword());
            System.out.println("비밀번호 일치 검사: " + member.isRightPassword(memberLoginRequest.getPassword()));

            if (!member.isRightPassword(memberLoginRequest.getPassword())) {
                System.out.println("잘 들어오나 ?");
                throw new RuntimeException("이메일 및 비밀번호 입력이 잘못되었습니다!");
            }

            UUID userToken = UUID.randomUUID();

            // redis 처리 필요
            redisService.deleteByKey(userToken.toString());
            redisService.setKeyAndValue(userToken.toString(), member.getId());
            
            return userToken.toString();
        }
        
        throw new RuntimeException("가입된 사용자가 아닙니다!");
    }

    @Override
    public Boolean applyNewPassword(EmailPasswordRequest emailPasswordRequest) {
        Optional<Authentication> maybeAuthentication = authenticationRepository.findByEmail(emailPasswordRequest.getEmail());
        if (!maybeAuthentication.isPresent()){
            return false;
        }
        BasicAuthentication authentication = (BasicAuthentication)maybeAuthentication.get();
        authentication.setPassword(emailPasswordRequest.getPassword());
        authenticationRepository.save(authentication);

        return true;
    }

    @Override
    public Boolean emailMatch(EmailMatchRequest toEmailMatchRequest) {
        Optional<Member> maybeMember = memberRepository.findByEmail(toEmailMatchRequest.getEmail());
        if (!maybeMember.isPresent()){
            return false;
        }

        return true;
    }
}
