package com.example.demo.forTest.classRoom;

import com.example.demo.domain.forTest.student.entity.ClassRoom;
import com.example.demo.domain.forTest.student.entity.TestStudent;
import com.example.demo.domain.forTest.student.repository.ClassRoomRepository;
import com.example.demo.domain.forTest.student.repository.TestStudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
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
        ClassRoom classRoom = new ClassRoom("여긴어디반");
        classRoomRepository.save(classRoom);

        TestStudent testStudent = new TestStudent("어디");

        testStudent.setClassRoom(classRoom);
        studentRepository.save(testStudent);
    }

    @Test
    public void 학생_번호_조회() {
        Optional<TestStudent> maybeStudent = studentRepository.findById(1L);
        TestStudent testStudent = maybeStudent.get();

        System.out.println(testStudent);
    }

    @Test
    public void 학생_전체_조회() {
        List<TestStudent> testStudentList = studentRepository.findAll();
        System.out.println(testStudentList);
    }

    @Test
    public void 반이름에_따른_학생_조회() {
        List<TestStudent> studentList = studentRepository.findStudentListByRoomName("두번째 클래스");
        System.out.println(studentList);
    }

    @Test
    public void 반_변경() {
        Optional<TestStudent> maybeStudent = studentRepository.findById(3L);
        TestStudent testStudent = maybeStudent.get();

        ClassRoom cr = new ClassRoom("새로운 반");
        classRoomRepository.save(cr);

        testStudent.setClassRoom(cr);
        studentRepository.save(testStudent);
    }
}
