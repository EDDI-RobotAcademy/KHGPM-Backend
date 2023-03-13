package com.example.demo.forTest.boardComment;

import com.example.demo.domain.forTest.entity.TestBoard;
import com.example.demo.domain.forTest.repository.TestBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardCommentTest {

    @Autowired
    private TestBoardRepository testBoardRepository;

    @Test
    public void 게시물_저장() {
        TestBoard board = new TestBoard("제목", "내용");
        testBoardRepository.save(board);
    }

}