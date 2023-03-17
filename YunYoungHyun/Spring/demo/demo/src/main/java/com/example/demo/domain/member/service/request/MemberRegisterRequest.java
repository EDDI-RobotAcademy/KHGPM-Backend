package com.example.demo.domain.member.service.request;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.MemberProfile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class MemberRegisterRequest {

    final private String email;
    final private String password;
    final private String city;
    final private String street;
    final private String addressDetail;
    final private String zipcode;

    // setter 를 쓰지 않기 위해 직접 Member 객체를 만들고 여기서 넣어주는 작업을 한 것이다.
    public Member toMember() {
        return new Member(
                email,
                MemberProfile.of(city, street, addressDetail, zipcode)
        );
    }
}