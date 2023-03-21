package com.example.demo.domain.product.controller.dto;

import com.example.demo.domain.product.entity.ImageResource;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class AllProductResponse {
    final private Long productId;
    final private String productName;
    final private String writer;
    final private Date regDate;
    final private List<ImageResource> imageResourceList;

    public AllProductResponse(Long productId, String productName, String writer,
                              Date regDate, List<ImageResource> imageResourceList) {

        this.productId = productId;
        this.productName = productName;
        this.writer = writer;
        this.regDate = regDate;
        this.imageResourceList = imageResourceList;
    }
}
