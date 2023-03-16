package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.dto.ProductRequest;
import com.example.demo.domain.product.controller.dto.ProductResponse;
import com.example.demo.domain.product.controller.dto.RequestProductInfo;
import com.example.demo.domain.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductResponse register(List<MultipartFile> fileList, RequestProductInfo productRequest);

    List<ProductResponse> list();

    Product read(Long productNo);

    void remove(Long productNo);

    Product modify(Long productNo, ProductRequest productRequest);

}