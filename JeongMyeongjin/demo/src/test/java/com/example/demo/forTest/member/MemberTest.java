package com.example.demo.forTest.member;

import com.example.demo.domain.member.service.MemberService;
import com.example.demo.domain.member.service.request.MemberLoginRequest;
import com.example.demo.domain.member.service.request.MemberRegisterRequest;
import com.example.demo.domain.product.controller.dto.ProductReadResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void 없는_이메일에대한_유효성_검증() {
        assertTrue(memberService.emailValidation("sdfshdkfshkfhsk@sds.com"));
    }

    @Test
    public void 회원가입_확인() {
        assertTrue(memberService.signUp(new MemberRegisterRequest(
                "test@test.com", "test",
                "집", "집", "집",
                "01234"
        )));
    }

    @Test
    public void 가입되지_않은_사용자_로그인_테스트() {
        assertThrows(
                RuntimeException.class,
                () ->
                        memberService.signIn(new MemberLoginRequest(
                                "sdjflsdjldgdkkkkkhkhkhkhkh@test.com", "test")));
    }

    @Test
    public void 아이디_및_비밀번호_틀린_상태_테스트() {
        assertThrows(
                RuntimeException.class,
                () ->
                        memberService.signIn(new MemberLoginRequest(
                                "test@test.com", "sdhflsdjfsgl")));
    }

    @Test
    public void 올바른_로그인_테스트() {
        String userToken = memberService.signIn(new MemberLoginRequest(
                "test@test.com", "test"));

        System.out.println("userToken: " + userToken);
    }

}