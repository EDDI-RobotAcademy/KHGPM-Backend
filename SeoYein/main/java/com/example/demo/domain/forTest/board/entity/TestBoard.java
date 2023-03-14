package com.example.demo.domain.forTest.board.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class TestBoard {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @OneToMany(mappedBy = "testBoard", fetch = FetchType.EAGER) // 네이밍규칙
    // 일대다 = 일은 현재 엔티티. 다는 여러개니까 리스트(코멘트)
    private List<Comment> comments = new ArrayList<>();

    public TestBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setComment (Comment comment) {
        comments.add(comment);
        comment.setTestBoard(this);
    }
}