package com.example.demo.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class ImageResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageResourcePath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;
    // 관계를 맺은 두 엔티티가 서로 toString을 호출하면서
    // 무한 반복되어 StackOverFlow 에러가 발생하는 경우로 인하여
    // @JsonBackReference로 직렬화 함

    public ImageResource(String imageResourcePath) {
        this.imageResourcePath = imageResourcePath;
    }


}