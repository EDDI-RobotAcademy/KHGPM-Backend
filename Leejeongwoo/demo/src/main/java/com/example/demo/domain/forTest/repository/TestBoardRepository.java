package com.example.demo.domain.forTest.repository;

import com.example.demo.domain.forTest.entity.TestBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestBoardRepository extends JpaRepository<TestBoard, Long> {
}