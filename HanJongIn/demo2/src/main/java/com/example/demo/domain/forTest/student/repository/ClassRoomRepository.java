package com.example.demo.domain.forTest.student.repository;

import com.example.demo.domain.forTest.student.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
}