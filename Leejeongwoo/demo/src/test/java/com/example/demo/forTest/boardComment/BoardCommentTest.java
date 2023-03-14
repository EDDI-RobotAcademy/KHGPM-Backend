package com.example.demo.forTest.boardComment;

import com.example.demo.domain.forTest.controller.response.CommentResponse;
import com.example.demo.domain.forTest.entity.Comment;
import com.example.demo.domain.forTest.entity.TestBoard;
import com.example.demo.domain.forTest.repository.CommentRepository;
import com.example.demo.domain.forTest.repository.TestBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        TestBoard board = new TestBoard("테스트보드", "너 삭제할꺼야");
        testBoardRepository.save(board);
    }

    @Test
    public void 댓댓() {
        Optional<TestBoard> mayTestBoard = testBoardRepository.findById(2L);
        TestBoard testBoard = mayTestBoard.get();

        Comment comment = new Comment("서이ㅓ 댓글ㅇㄱ");

        testBoard.setComment(comment);

        commentRepository.save(comment);
    }

    @Test
    public void 게시물_댓_확인() {
        List<Comment> commentList = commentRepository.findAllCommentsByBoardId(1L);
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comment comment : commentList) {
            commentResponses.add(new CommentResponse(comment.getContent()));
        }

        System.out.println(commentResponses);
    }

    @Test
    public void 댓_수정() {
        Optional<Comment> maybeComment = commentRepository.findById(3L);
        Comment comment = maybeComment.get();
        comment.changeContent("알랄f라라라랄랄");
        commentRepository.save(comment);
    }

    @Test
    public void 댓_삭제() {
        commentRepository.deleteById(5L);
    }

    @Test
    public void 게시글_삭() {
        final Long boardId = 2L;
        List<Comment> commentList = commentRepository.findAllCommentsByBoardId(boardId);

        for (Comment comment : commentList) {
            System.out.println("comment 내용: " + comment.getContent());
            commentRepository.delete(comment);
        }

        testBoardRepository.deleteById(boardId);
    }
}