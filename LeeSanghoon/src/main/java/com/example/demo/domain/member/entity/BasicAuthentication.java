package com.example.demo.domain.member.entity;

import com.example.demo.domain.utility.encrypt.EncryptionUtil;
import com.example.demo.domain.utility.password.PasswordHashConverter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;

public class BasicAuthentication extends Authentication {

    @Setter
    @Column(nullable = false)
    @Convert(converter = PasswordHashConverter.class)
    private String password;

    public BasicAuthentication (Member member, String authenticationType, String password) {
        super(member, authenticationType);
        this.password = password;
    }

    public boolean isRightPassword(String plainToCheck) {
        return EncryptionUtil.checkValidation(plainToCheck, password);
    }
}
