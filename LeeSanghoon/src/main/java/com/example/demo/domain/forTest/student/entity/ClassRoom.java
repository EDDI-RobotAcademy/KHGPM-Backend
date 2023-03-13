package com.example.demo.domain.forTest.student.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class ClassRoom {

    @Id
    @Column(name = "class_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classRoomId;

    private String roomName;

    public ClassRoom(String roomName) { this.roomName = roomName; }
}
