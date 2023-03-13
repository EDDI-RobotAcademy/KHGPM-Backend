package com.example.demo.domain.forTest.student.repository;

import com.example.demo.domain.forTest.student.entity.ClassRoom;
import com.example.demo.domain.forTest.student.entity.TestStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

}