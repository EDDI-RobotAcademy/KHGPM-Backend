package com.example.demo.domain.board.service;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    final private BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public void register(BoardRequest boardRequest) {
        Board board = new Board();
        // 요청받은 데이터 값으로 set
        board.setTitle(boardRequest.getTitle());
        board.setWriter(boardRequest.getWriter());
        board.setContent(boardRequest.getContent());

        boardRepository.save(board);
        // JpaRepository를 상속받으면 기본적으로 제공하는 메서드 중 하나인 save
    }

    @Override
    public List<Board> list() {  //boardId 내림차순으로 리스트 출력
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "boardId"));
    }

    @Override
    public Board read(Long boardId) {
        // ~일수도 있고 아닐수도 있고 (null일수도 있는 객체를 감싸는 일종의 wrapper 클래스)
        Optional<Board> maybeBoard = boardRepository.findById(boardId);

        if(maybeBoard.isEmpty()) {
            log.info("읽을수없음");
            return null;
        }

        return maybeBoard.get();
    }

    @Override
    public void remove(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Override
    public Board modify(Long boardId, BoardRequest boardRequest) {
        Optional<Board> maybeBoard = boardRepository.findById(boardId);

        if(maybeBoard.isEmpty()) {
            System.out.println("board정보 찾지못함: " + boardId);
            return null;
        }

        Board board = maybeBoard.get();
        board.setTitle(boardRequest.getTitle());
        board.setContent(boardRequest.getContent());

        boardRepository.save(board);

        return board;
    }
}