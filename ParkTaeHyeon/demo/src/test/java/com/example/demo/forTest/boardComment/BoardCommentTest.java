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
        TestBoard board = new TestBoard("삭제용 게시물", "삭제용");
        testBoardRepository.save(board);
    }

    @Test
    public void 덧글_저장() {
        Optional<TestBoard> mayTestBoard = testBoardRepository.findById(4L);
        TestBoard testBoard = mayTestBoard.get();

        Comment comment = new Comment("삭제용!!!!");
//        Comment comment = new Comment("덧글 추가");


        testBoard.setComment(comment);
        testBoardRepository.save(testBoard);

        commentRepository.save(comment);
        // 원래 있던 '1L, 제목, 내용'에 대한 게시글에 대해 comment를 save(update)한 것
    }

    @Test
    public void 게시물_덧글_출력() {
        List<Comment> commentList = commentRepository.findAllCommentsByBoardId(1L);
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comment comment: commentList) {
            commentResponses.add(new CommentResponse(comment.getContent()));
        }
        System.out.println(commentResponses);
    }
    @Test
    public void 덧글_수정() {
        Optional<Comment> maybeComment = commentRepository.findById(3L);
        // boardId가 아닌 comment의 id에 대한 수정
        Comment comment = maybeComment.get();

        comment.changeContent("이거로 바꿀꺼");
        commentRepository.save(comment);
    }

    @Test
    public void 덧글_삭제() {
        commentRepository.deleteById(5L);
    }

    @Test
    public void 게시글_삭제() {
        final Long boardId = 2L;

        List<Comment> commentList = commentRepository.findAllCommentsByBoardId(boardId);

        for(Comment comment: commentList) {
            System.out.println("comment 내용: " + comment.getContent());
            commentRepository.delete(comment);
        }
        testBoardRepository.deleteById(boardId);
    }
}