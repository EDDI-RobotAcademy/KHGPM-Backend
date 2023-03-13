package com.example.demo.domain.forTest.student.repository;

import com.example.demo.domain.forTest.student.entity.TestStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestStudentRepository extends JpaRepository<TestStudent, Long> {
    @Query("select ts from TestStudent ts join ts.classRoom cr where cr.roomName = :roomName")
    List<TestStudent> findStudentListByRoomName(String roomName);
}