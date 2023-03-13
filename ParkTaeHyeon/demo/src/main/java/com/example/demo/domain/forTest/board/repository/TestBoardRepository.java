package com.example.demo.domain.forTest.board.repository;

import com.example.demo.domain.forTest.board.entity.TestBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestBoardRepository extends JpaRepository<TestBoard, Long> {
}