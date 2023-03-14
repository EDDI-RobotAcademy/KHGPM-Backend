package com.example.demo.forTest.classRoom;

import com.example.demo.domain.forTest.student.entity.ClassRoom;
import com.example.demo.domain.forTest.student.entity.TestStudent;
import com.example.demo.domain.forTest.student.repository.ClassRoomRepository;
import com.example.demo.domain.forTest.student.repository.TestStudentRepository;
import net.bytebuddy.build.ToStringPlugin;
import org.assertj.core.api.OptionalAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ClassRoomTest {

    @Autowired
    private TestStudentRepository studentRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Test
    public void 학생_정보_저장() {
        ClassRoom classRoom = new ClassRoom("첫번째 클래스");
        classRoomRepository.save(classRoom);

        TestStudent testStudent = new TestStudent("김철수");

        testStudent.setClassRoom(classRoom);
        studentRepository.save(testStudent);
    }

    @Test
    public void 학생_번호_조회() {
        Optional<TestStudent> maybeStudent = studentRepository.findById(1L);
        TestStudent testStudent = maybeStudent.get();

        System.out.println("학생 번호 조회: "+ testStudent);
    }

    @Test
    public void 학생_전체_조회() {
        List<TestStudent> studentList = studentRepository.findAll();
        System.out.println("학생 전체 조회: "+ studentList);
    }

    @Test
    public void 반이름에_따른_학생_조회() {
        List<TestStudent> studentList = studentRepository.findStudentListByRoomName("두번째 클래스");
        System.out.println("반이름에 따른 학생 조회: "+ studentList);
    }

    @Test
    public void 새로운_반으로_변경() {
        Optional<TestStudent> maybeStudent = studentRepository.findById(5L);
        TestStudent testStudent = maybeStudent.get();

        ClassRoom cr = new ClassRoom("새로운 반");
        classRoomRepository.save(cr);

        testStudent.setClassRoom(cr);
        studentRepository.save(testStudent);
    }
    @Test
    public void 현재있는_반으로_변경() {
        Optional<TestStudent> maybeStudent = studentRepository.findById(5L);
        TestStudent testStudent = maybeStudent.get();

        Optional<ClassRoom> maybeClassRoom = classRoomRepository.findByRoomName("첫번째 클래스");
        ClassRoom classRoom = maybeClassRoom.get();
        classRoomRepository.save(classRoom);

        testStudent.setClassRoom(classRoom);
        studentRepository.save(testStudent);
    }

    @Test
    public void 삭제() {
        Optional<TestStudent> maybeStudent = studentRepository.findById(5L);
        TestStudent testStudent = maybeStudent.get();

        testStudent.setClassRoom(null);
        studentRepository.delete(testStudent);
    }

}