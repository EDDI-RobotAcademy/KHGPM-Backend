package com.example.demo.domain.forTest.entity;

import lombok.CustomLog;
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

    //해당 TestBoard 에 속한 Comment 들
    @OneToMany(mappedBy = "testBoard", fetch = FetchType.EAGER) // 1(TestBoard):N(Comment),
    private List<Comment> comments = new ArrayList<>();

    public TestBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setComment(Comment comment) {
        comments.add(comment);
        comment.setTestBoard(this);
    }
}
