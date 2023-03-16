package com.example.demo.domain.forTest.student.repository;

import com.example.demo.domain.forTest.student.entity.TestStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestStudentRepository extends JpaRepository<TestStudent, Long> {

    @Query("select ts from TestStudent ts join ts.classRoom cr where cr.roomName = :roomName")
    List<TestStudent> findStudentListByRoomName(String roomName);

    // SQL과 유사한 JQPL (Java Persistence Query Language) 라는 객체지향 쿼리 언어를 통해 복잡한 쿼리 처리를 지원
    // JPQL - 테이블이 아닌 엔티티 객체를 대상으로 검색하는 객체지향 쿼리, SQL 추상화로 인해 특정 DB SQL에 의존하지 않음
}