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
    // 속성(feld), setter, method, constructor에서 사용하며 Type에 따라 알아서 Bean을 주입해준다.
    // 무조건적인 객체에 대한 의존성을 주입시키며, 스프링이 자동적으로 값을 할당한다.
    private TestStudentRepository studentRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Test
    public void 학생_정보_저장() {
        ClassRoom classRoom = new ClassRoom("여긴 어디반");
        classRoomRepository.save(classRoom);

        TestStudent testStudent = new TestStudent("나는누구");

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

        ClassRoom cr = new ClassRoom("새 반 생성 후 변경");
        classRoomRepository.save(cr);

        testStudent.setClassRoom(cr);
        studentRepository.save(testStudent);
    }

    @Test
    public void 삭제() {
        Optional<TestStudent> maybeStudent = studentRepository.findById(4L);
        TestStudent testStudent = maybeStudent.get();

        testStudent.setClassRoom(null);
        studentRepository.delete(testStudent);
    }
}