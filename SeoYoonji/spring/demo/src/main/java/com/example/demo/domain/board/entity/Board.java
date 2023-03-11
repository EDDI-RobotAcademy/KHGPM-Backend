package com.example.demo.domain.board.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
// @Entity 를 붙이면 JPA 가 관리하는 클래스임을 명시
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본키 자동 생성 (IDENTITY전략 : 기본키 생성을 DB에 위임)
    private Long boardId;

    @Column(length = 128, nullable = false)
    // 테이블의 컬럼. 옵션 변경을 위해선 선언해야함 (디폴트 길이 255이고 nullagle=false 는 not null 제약조건)
    private String title;

    @Column(length = 32, nullable = false)
    private String writer;

    @Lob
    // DB의 BLOB(이진 대형 객채), CLOB(문자 대형 객체) 타입과 맵핑
    private String content;

    @CreationTimestamp
    // INSERT 쿼리가 발생할 때, 현재 시간을 값으로 채워 생성(데이터 생성 시점 관리 필요 없음)
    private Date regDate;

    @UpdateTimestamp
    // UPDATE 쿼리가 발생할 때, 현재 시간을 값으로 채워 생성(수정 시간 관리 필요 없음)
    private Date updDate;
}
