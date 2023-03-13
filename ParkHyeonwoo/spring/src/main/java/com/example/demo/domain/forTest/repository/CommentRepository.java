package com.example.demo.domain.forTest.repository;

import com.example.demo.domain.forTest.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
