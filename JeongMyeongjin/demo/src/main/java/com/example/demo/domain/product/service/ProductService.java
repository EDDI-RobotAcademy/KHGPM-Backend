package com.example.demo.domain.product.service;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.product.controller.request.ProductRequest;
import com.example.demo.domain.product.entity.Product;

import java.util.List;

public interface ProductService {
    void register(ProductRequest productRequest);

    List<Product> list();

    Product read(Long productId);

    void remove(Long productId);

    Product modify(Long productId, ProductRequest productRequest);
}