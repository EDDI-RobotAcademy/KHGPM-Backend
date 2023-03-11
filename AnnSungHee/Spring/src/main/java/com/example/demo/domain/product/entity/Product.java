package com.example.demo.domain.product.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data   // 클래스 내부에 필드들의 getter, setter, toString, equals, hashCode 등을 자동으로 생성
@Entity // JPA에서 엔티티 클래스임을 나타내기 위한 애노테이션
/**
 * Spring Boot 프레임워크에서 JPA를 사용하여 게시판(Product) 엔티티 클래스를 정의
 */
public class Product {

    @Id // 해당 필드가 엔티티의 주키(primary key)임을 나타내는 애노테이션입니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 엔티티의 주키 값을 자동으로 생성하기 위한 애노테이션
    // GenerationType.IDENTITY는 데이터베이스가 자동으로 생성해주는 자동증가형 컬럼을 사용하는 것을 의미
    private Long productId;

    @Column(length = 128, nullable = false)
    // 엔티티 필드에 대한 매핑 정보를 설정하는 애노테이션
    // length는 문자열의 길이를 지정하고, nullable은 null 값을 허용할지 여부를 지정
    private String name;

    @Column(nullable = false)
    // 엔티티 필드에 대한 매핑 정보를 설정하는 애노테이션
    // 해당 코드는 int(정수형)이기 때문에 length는 지정필요 없음 nullable만 설정
    private int price;
}