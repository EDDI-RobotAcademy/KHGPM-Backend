package com.example.demo.domain.board.repository;
import com.example.demo.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 필드명 boardId였는데 id 박아놨음 ;;;
    List<Board> findByBoardIdAndWriter(Long boardId, String writer);
    Long countBy();

    Board findFirstByOrderByBoardIdDesc();
}