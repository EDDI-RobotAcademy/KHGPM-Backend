package com.example.demo.forTest.classRoom;

import com.example.demo.domain.forTest.student.entity.ClassRoom;
import com.example.demo.domain.forTest.student.entity.TestStudent;
import com.example.demo.domain.forTest.student.repository.ClassRoomRepository;
import com.example.demo.domain.forTest.student.repository.TestStudentRepository;
import net.minidev.json.JSONUtil;
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
        ClassRoom classRoom = new ClassRoom("나는 3반");
        classRoomRepository.save(classRoom);

        TestStudent testStudent = new TestStudent("강백호");
        testStudent.setClassRoom(classRoom);
        studentRepository.save(testStudent);
    }

    //특정 번호로 조회 하는방법
    @Test
    public void 학생_번호로_조회() {
        Optional<TestStudent> maybeStudent = studentRepository.findById(1L);
        TestStudent testStudent = maybeStudent.get();
        System.out.println(testStudent);
    }

    @Test
    public void 학생_전체_조회() {
        List<TestStudent> testStudentList = studentRepository.findAll();
        System.out.println(testStudentList);
    }

    // 반 검색하면 맞는 학생들 출력
    @Test
    public void 반이름에_따른_학생_조회() {
        List<TestStudent> studentList = studentRepository.findStudentListByRoomName("첫번째 클래스");
        System.out.println(studentList);
    }

    @Test
    public void 반_이동() {
        //id로 학생 조회 한다음에
        Optional<TestStudent> maybeStudent = studentRepository.findById(3L);
        TestStudent testStudent = maybeStudent.get();

        //새로운 신규 클래스 생성 후 저장
        ClassRoom cr = new ClassRoom("새로운 반");
        classRoomRepository.save(cr);

        testStudent.setClassRoom(cr);
        studentRepository.save(testStudent);
    }

    @Test
    public void 삭제() {
        Optional<TestStudent> maybeStudent = studentRepository.findById(2L);
        TestStudent testStudent = maybeStudent.get();
        //학생은 삭제가 되어도 반은 유지되어야 하기 때문에 null처리
//        testStudent.setClassRoom(null);
        studentRepository.delete(testStudent);
    }
}