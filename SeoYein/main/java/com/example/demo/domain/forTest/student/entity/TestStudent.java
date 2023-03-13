package com.example.demo.domain.forTest.student.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class TestStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "class_room_id") // ClassRoom 컬럼과 TestStudent 컬럼이 join한다.
    private ClassRoom classRoom;

    public TestStudent(String name) {
        this.name = name;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
