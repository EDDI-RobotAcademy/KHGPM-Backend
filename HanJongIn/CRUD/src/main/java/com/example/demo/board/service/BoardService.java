package com.example.demo.board.service;

import com.example.demo.board.controller.request.BoardRequest;
import org.springframework.stereotype.Service;


public interface BoardService {
    public void register(BoardRequest boardRequest);
}
