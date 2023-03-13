package com.example.demo.domain.forTest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER) // 각각의 TestBoard를 가르키게 한다.
    @JoinColumn(name = "board_id")
    private TestBoard testBoard;

    public Comment(String content) {
        this.content = content;
    }
}
