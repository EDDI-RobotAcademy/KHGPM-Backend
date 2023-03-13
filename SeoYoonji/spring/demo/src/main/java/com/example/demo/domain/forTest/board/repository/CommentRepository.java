package com.example.demo.domain.forTest.board.repository;

import com.example.demo.domain.forTest.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c join c.testBoard tb where tb.id = :boardId")
    List<Comment> findAllCommentsByBoardId(Long boardId);
}
