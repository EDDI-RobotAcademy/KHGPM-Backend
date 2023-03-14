package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.dto.ProductRequest;
import com.example.demo.domain.product.entity.Product;

import java.util.List;

public interface ProductService {
//    void register(ProductRequest productRequest);

    void register(ProductRequest productRequest, String imgsrc);

    List<Product> list();

    Product read(Long productId);

    void remove(Long productId);

    Product modify(Long productId, ProductRequest productRequest);
}
