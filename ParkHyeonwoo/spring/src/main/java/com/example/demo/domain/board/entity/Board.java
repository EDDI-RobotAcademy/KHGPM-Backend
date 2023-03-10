package com.example.demo.domain.board.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data // getter, setter 자동 생성
@Entity
// class 이름으로 DB 가 생성된다.
public class Board {

    // 직접 할당 -> @Id 만 사용
    // 자동 생성 -> @Id 와 @GeneratedValue 를 같이 사용
    @Id // Primarykey(기본키) 로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 새로운 PrimaryKey 를 생성해준다
    private Long boardId;

    @Column(length = 128, nullable = false) // 문자 길이(String 타입에만 사용), default로 null 값의 허용 여부 결정
    private String title;

    @Column(length = 32, nullable = false)
    private String writer;

    @Lob // Large Object 의 줄임말, 대형 객체를 할당할 때 사용
    private String content;

    @CreationTimestamp // INSERT 쿼리를 날릴 때 현재 시간 할당
    private Date regDate;

    @UpdateTimestamp // UPDATE 쿼리 날릴 때 현재 시간 할당
    private Date updDate;
}