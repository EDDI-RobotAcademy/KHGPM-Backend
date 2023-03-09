package com.example.demo.board.service;

import com.example.demo.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    final private BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
}
