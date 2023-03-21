package com.example.demo.domain.member.controller.form;

import com.example.demo.domain.member.service.request.EmailMatchRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmailMatchForm {

    private String email;

    public EmailMatchRequest toEmailMatchRequest() {
        return new EmailMatchRequest(email);
    }
}