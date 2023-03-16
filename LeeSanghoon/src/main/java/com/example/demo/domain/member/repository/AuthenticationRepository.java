package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
}
