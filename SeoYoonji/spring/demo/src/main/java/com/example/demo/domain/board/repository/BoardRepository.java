package com.example.demo.domain.board.repository;

import com.example.demo.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
//Entity에 의해 생성된 DB에 접근하는 메서드들을 사용하기 위한 인터페이스
public interface BoardRepository extends JpaRepository<Board, Long> {
                                      // <Entity 클래스명, Entity 클래스의 PK 자료형>
}
