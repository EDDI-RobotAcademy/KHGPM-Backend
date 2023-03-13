package com.example.demo.domain.forTest.student.repository;

import com.example.demo.domain.forTest.student.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

    Optional<ClassRoom> findByRoomName(String roomName);

}