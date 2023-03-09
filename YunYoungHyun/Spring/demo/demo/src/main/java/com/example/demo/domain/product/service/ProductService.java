package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.request.ProductRequest;
import com.example.demo.domain.product.entity.Product;

import java.util.List;

public interface ProductService {

    public void register(ProductRequest productRequest);

    List<Product> list();

    Product read(Long boardId);

    void remove(Long boardId);

    Product modify(Long productNo, ProductRequest productRequest);

}