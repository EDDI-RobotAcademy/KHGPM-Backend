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

    private Long productId;
    private String productName;
    private String writer;
    private String content;
    private Integer price;
    private Date regDate;
}
