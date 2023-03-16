package com.example.demo;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.repository.BoardRepository;
import com.example.demo.domain.board.service.BoardService;
import com.example.demo.domain.board.service.BoardServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private BoardRepository boardRepository;

	@Test
	void boardTest() {

		Optional<Board> maybeBoard = boardRepository.findById(9L);
		Board board = maybeBoard.get();

		BoardRequest boardRequest = new BoardRequest(
				board.getTitle(), board.getWriter(), board.getContent());

		List<Board> conditionMatchingBoardList =
				boardRepository.findByBoardIdAndWriter(9L, boardRequest.getWriter());

		System.out.println("result: " + conditionMatchingBoardList.get(0));
	}

}
