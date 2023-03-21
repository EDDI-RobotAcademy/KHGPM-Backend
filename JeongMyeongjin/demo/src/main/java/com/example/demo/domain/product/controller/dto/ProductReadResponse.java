package com.example.demo.domain.product.controller.dto;

import com.example.demo.domain.product.entity.ImageResource;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class ProductReadResponse {

    // 상품 상세 정보를 응답하기 위한 객체

    private Long productId;
    private String productName;
    private String writer;
    private String content;
    private Integer price;
    private Date regDate;
}