package com.example.demo.domain.forTest.board.repository;

import com.example.demo.domain.forTest.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c join c.testBoard tb where tb.id = :boardId") // 콜론을 붙여서 밑줄의 Long boardId를 가져오는 것
    // Comment와 testBoard의 내용이 필요하므로 join을 사용한다.
    List<Comment> findAllCommentsByBoardId(Long boardId);
}