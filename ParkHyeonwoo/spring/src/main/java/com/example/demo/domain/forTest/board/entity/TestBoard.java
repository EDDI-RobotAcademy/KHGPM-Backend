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

    //해당 TestBoard 에 속한 Comment 들
    // 1(TestBoard):N(Comment)
    // mappedBy : 양방향 관계에서 주체가 되는 쪽(외래키가 있는 쪽, 지금은 Comment)의 반대 객체에 선언한다.
    @OneToMany(mappedBy = "testBoard", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    public TestBoard(String title, String content) { // 새로운 게시글 생성
        this.title = title;
        this.content = content;
    }

    public void setComment(Comment comment) { // 댓글 저장
        comments.add(comment);
        comment.setTestBoard(this); // 게시물과 댓글 매핑
    }
}
