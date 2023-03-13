package com.example.demo.forTest.boardComment;

import com.example.demo.domain.forTest.controller.response.CommentResponse;
import com.example.demo.domain.forTest.entity.Comment;
import com.example.demo.domain.forTest.entity.TestBoard;
import com.example.demo.domain.forTest.repository.CommentRepository;
import com.example.demo.domain.forTest.repository.TestBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BoardCommentTest {
    @Autowired
    private TestBoardRepository testBoardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void 게시물_저장() {
        TestBoard board = new TestBoard("제목2", "내용2");
        testBoardRepository.save(board);
    }

    @Test
    public void 덧글_저장() {
        Optional<TestBoard> mayTestBoard = testBoardRepository.findById(2L);
        TestBoard testBoard = mayTestBoard.get();

        Comment comment = new Comment("댓글추가");

        testBoard.setComment(comment);
        testBoardRepository.save(testBoard); //원래있던 게시글에 댓글을 추가한 것이니 '수정' 한거라 볼 수 있음

        commentRepository.save(comment);
    }

    @Test
    public void 게시물_덧글_출력() {
        List<Comment> commentList = commentRepository.findAllCommentsByBoardId(1L);
        List<CommentResponse> commentResponses = new ArrayList<>();

        for(Comment comment: commentList) {
            commentResponses.add(new CommentResponse(comment.getContent()));
        }
        
        System.out.println(commentResponses);
    }
}
