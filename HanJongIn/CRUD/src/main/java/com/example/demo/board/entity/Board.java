package com.example.demo.board.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bno;

    @Column(length = 255, nullable = false)
    private String title;

    @Lob
    private String content;

    @Column(length = 32, nullable = false)
    private String writer;

    @Column
    private int view_cnt;

    @Column
    private int comment_cnt; //댓글 갯수

    @CreationTimestamp
    private Date reg_date;

    @UpdateTimestamp
    private Date up_date;


}
