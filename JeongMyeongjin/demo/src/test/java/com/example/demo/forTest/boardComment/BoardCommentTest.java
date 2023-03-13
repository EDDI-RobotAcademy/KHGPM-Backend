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
        TestBoard board = new TestBoard("제목", "내용");
        testBoardRepository.save(board);
    }

    @Test
    public void 덧글_저장() {
        Optional<TestBoard> mayTeatBoard = testBoardRepository.findById(1L);
        TestBoard testBoard = mayTeatBoard.get();

        Comment comment = new Comment("덧글");

        testBoard.setComment(comment);
        testBoardRepository.save(testBoard);

        commentRepository.save(comment);
    }

    @Test
    public void 게시물_덧글_출력() {
        List<Comment> commentList = commentRepository.findAllCommentsByBoardId(2L);
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comment comment: commentList) {
            commentResponses.add(new CommentResponse(comment.getContent()));
        }

        System.out.println(commentResponses);
    }
}
