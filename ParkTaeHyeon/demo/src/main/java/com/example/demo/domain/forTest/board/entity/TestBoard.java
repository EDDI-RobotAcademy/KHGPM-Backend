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

    @OneToMany(mappedBy = "testBoard", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    // 1:N , TestBoard를 중심으로 !
    // TestBoard는 Comment를 알고 싶은데 Comment는 TestBoard를 알고 싶지 않음.
    // DB 입장에서 TestBoard에 FK 걸어야한다. (일대다 관계는 항상 다(N)에 외래 키가 있다.

    public TestBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setComment (Comment comment) {
        comments.add(comment);
        comment.setTestBoard(this);
    }
    // 단순한 Test용의 메소드라서 Entity에 Service Method가 존재하고 있다.
}