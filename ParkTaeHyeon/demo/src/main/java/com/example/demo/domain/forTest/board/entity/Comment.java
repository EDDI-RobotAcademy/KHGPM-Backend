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
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id") // 외래키
    private TestBoard testBoard;

    // Comment : N - TestBoard : 1
    // Comment에서 TestBoard를 참조한다.
    // 외래 키가 있는 곳에 참조를 걸고 연관관계 매핑을 한다.

    public Comment (String content) {
        this.content = content;
    }

    public void changeContent (String content) {
        this.content = content;
    }
}
