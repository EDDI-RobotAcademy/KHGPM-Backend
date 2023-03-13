package com.example.demo.forTest.boardComment;

import com.example.demo.domain.forTest.entity.Comment;
import com.example.demo.domain.forTest.entity.TestBoard;
import com.example.demo.domain.forTest.repository.CommentRepository;
import com.example.demo.domain.forTest.repository.TestBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
