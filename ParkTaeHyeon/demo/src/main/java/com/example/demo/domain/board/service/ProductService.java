package com.example.demo.domain.board.service;

import com.example.demo.domain.board.controller.request.ProductRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.entity.Product;

import java.util.List;

public interface ProductService {

    public void register(ProductRequest productRequest);

    List<Product> list();

    Product read(Long productId);
}
