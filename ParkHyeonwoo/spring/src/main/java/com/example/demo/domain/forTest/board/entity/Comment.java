package com.example.demo.domain.forTest.board.entity;

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

    @ManyToOne(fetch = FetchType.EAGER) // N:1, 각각의 TestBoard를 가르킨다.
    @JoinColumn(name = "board_id") // TestBoard 의 board_id 와 매핑, 외래키 의미
    private TestBoard testBoard;

    public Comment(String content) {
        this.content = content;
    }

    public void changeContent (String content) {
        this.content = content;
    }
}
