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

    @ManyToOne(fetch = FetchType.EAGER) //게시글 하나에 댓글 여러개 달 수 있으니 다대일
    @JoinColumn(name = "board_id")
    private TestBoard testBoard;

    public Comment (String content) {
        this.content = content;
    }

    public void changeContent (String content) {
        this.content = content;
    }
}
