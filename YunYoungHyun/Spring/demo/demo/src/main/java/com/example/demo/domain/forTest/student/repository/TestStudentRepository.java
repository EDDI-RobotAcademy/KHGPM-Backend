package com.example.demo.domain.forTest.student.repository;

import com.example.demo.domain.forTest.student.entity.TestStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestStudentRepository extends JpaRepository<TestStudent, Long> {



}