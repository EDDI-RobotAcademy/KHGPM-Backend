package com.example.demo.domain.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Member {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable = false)
    private String email;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private MemberProfile memberProfile;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<Authentication> authentications = new HashSet<>();

    public Member(String email, MemberProfile memberProfile) {
        this.email = email;
        this.memberProfile = memberProfile;
        memberProfile.setMember(this);
    }

    public boolean isRightPassword(String plainToCheck) {
        final Optional<Authentication> maybeBasicAuth = findBasicAuthentication();

        if (maybeBasicAuth.isPresent()) {
            final BasicAuthentication authentication = (BasicAuthentication) maybeBasicAuth.get();
            return authentication.isRightPassword(plainToCheck);
        }

        return false;
    }

    private Optional<Authentication> findBasicAuthentication () {
        return authentications
                .stream()
                .filter(authentication -> authentication instanceof BasicAuthentication)
                .findFirst();
    }
}