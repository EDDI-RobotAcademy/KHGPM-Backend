package com.example.demo.domain.board.repository;

import com.example.demo.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * extends JpaRepository 가 있으면 Spring Data JPA가 인터페이스에 대해서 프록시 구현체를 만든 뒤 DI 받는다.
 * save(S) : 새로운 엔티티를 저장하고, 이미 있는 엔티티는 병합한다.
 * delete(T) : 삭제
 * findById(ID) : 엔티티를 하나 조회한다.
 * getOne(ID) : 엔티티를 프록시로 조회한다. entityManager.getReference()
 * findAll(..) : 모든 엔티티를 조회한다. (Paging, Sorting 가능)
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

    Long countBy();

    Board findFirstByOrderByBoardIdDesc(); // 내림차순 정렬 후 첫번째 BoardId 선택
}