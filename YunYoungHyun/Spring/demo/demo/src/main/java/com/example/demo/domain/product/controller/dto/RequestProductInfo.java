package com.example.demo.domain.product.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestProductInfo {

     final private String name;
     final private Long  price;
     final private String writer;
     final private String content;

//     @JsonCreator

}