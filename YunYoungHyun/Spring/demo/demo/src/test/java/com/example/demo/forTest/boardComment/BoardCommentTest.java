package com.example.demo.forTest.boardComment;

import com.example.demo.domain.forTest.board.controller.response.CommentResponse;
import com.example.demo.domain.forTest.board.entity.Comment;
import com.example.demo.domain.forTest.board.entity.TestBoard;
import com.example.demo.domain.forTest.board.repository.CommentRepository;
import com.example.demo.domain.forTest.board.repository.TestBoardRepository;
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
    public void 댓글_저장() {
        Optional<TestBoard> maybeTestBoard = testBoardRepository.findById(1L);
        TestBoard testBoard = maybeTestBoard.get();

        Comment comment = new Comment("댓글");
        testBoard.setComment(comment);

        testBoardRepository.save(testBoard);
        commentRepository.save(comment);
    }

    @Test
    public void 게시물_댓글_출력() {
        List<Comment> commentList = commentRepository.findAllCommentsByBoardId(1L);
        List<CommentResponse> commentResponses = new ArrayList<>();

        for(Comment comment: commentList) {
            commentResponses.add(new CommentResponse(comment.getContent()));
        }

        System.out.println("게시물 댓글 출력: "+ commentResponses);
    }

    @Test
    public void 댓글_수정() {
        Optional<Comment> maybeComment = commentRepository.findById(4L);
        Comment comment = maybeComment.get();

        comment.updateContent("이러쿵 저러쿵 요로쿵");
        commentRepository.save(comment);
    }

    @Test
    public void 댓글_삭제() {
        commentRepository.deleteById(3L);
    }

    @Test
    public void 게시글_삭제() {
        final Long boardId = 3L;
        List<Comment> commentList = commentRepository.findAllCommentsByBoardId(boardId);

        for(Comment comment: commentList) {
            System.out.println("게시글_삭제: "+ comment.getContent());
            commentRepository.delete(comment);
        }

        testBoardRepository.deleteById(boardId);
    }

}