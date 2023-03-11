package com.example.demo.domain.board.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data   // 클래스 내부에 필드들의 getter, setter, toString, equals, hashCode 등을 자동으로 생성
@Entity // JPA에서 엔티티 클래스임을 나타내기 위한 애노테이션
/**
 * Spring Boot 프레임워크에서 JPA를 사용하여 게시판(Board) 엔티티 클래스를 정의
 */
public class Board {

    @Id // 해당 필드가 엔티티의 주키(primary key)임을 나타내는 애노테이션입니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 엔티티의 주키 값을 자동으로 생성하기 위한 애노테이션
    // GenerationType.IDENTITY는 데이터베이스가 자동으로 생성해주는 자동증가형 컬럼을 사용하는 것을 의미
    private Long boardId;

    @Column(length = 128, nullable = false)
    // 엔티티 필드에 대한 매핑 정보를 설정하는 애노테이션
    // length는 문자열의 길이를 지정하고, nullable은 null 값을 허용할지 여부를 지정
    private String title;

    @Column(length = 32, nullable = false)
    private String writer;

    @Lob // 대용량 데이터를 저장하기 위한 애노테이션
    private String content;

    @CreationTimestamp // 엔티티가 생성될 때 자동으로 날짜를 설정하기 위한 애노테이션
    private Date regDate;

    @UpdateTimestamp // 엔티티가 업데이트될 때 자동으로 날짜를 업데이트하기 위한 애노테이션
    private Date updDate;
}