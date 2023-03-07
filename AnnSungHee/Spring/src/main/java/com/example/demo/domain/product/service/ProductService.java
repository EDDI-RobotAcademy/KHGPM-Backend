package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.request.ProductRequest;
import com.example.demo.domain.product.entity.Product;

import java.util.List;

public interface ProductService {
    public void register(ProductRequest productRequest);
    // 게시글 등록을 위해 BoardRequest 객체를 매개변수로 받는다.

    List<Product> list();
    // 모든 게시글을 조회하여 List<Board> 형태로 반환합니다.

    Product read(Long boardId);
    // 매개변수로 게시글의 고유 ID인 boardID를 받고서 해당 ID 의 게시글 상세 정보를 조회하여 Board 객체로 받는다.

    void remove(Long boardId);
    // 매개변수로 게시글의 고유 ID인 boardID를 받고서 해당 ID 의 게시글 상세 정보를 조회하여 해당 게시글을 삭제한다.
    Product modify(Long boardId, ProductRequest productRequest);
}