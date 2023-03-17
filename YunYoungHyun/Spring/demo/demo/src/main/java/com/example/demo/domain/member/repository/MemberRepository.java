package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // FETCH TYPE LAZY 에 의해 proxy 참조가 안돼서 join fetch 로
    @Query("select m from Member m join fetch m.authentications where m.email = :email")
    Optional<Member> findByEmail(String email);
}