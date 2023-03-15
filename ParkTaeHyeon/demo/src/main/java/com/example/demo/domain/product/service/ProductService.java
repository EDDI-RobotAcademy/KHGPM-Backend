package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.dto.ProductRequest;
import com.example.demo.domain.product.controller.dto.ProductResponse;
import com.example.demo.domain.product.controller.dto.RequestProductInfo;
import com.example.demo.domain.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    
        void register(List<MultipartFile> imageFileList, RequestProductInfo productRequest);

     //  List<Product> list();
    // 현재 Board UI 에서는 반환되어야할 타입이 id, productName, writer, regData
    // so that's why I just made 'ResponseProduct'(.DTO)
    // it could be required modification depends on UI

       List<ProductResponse> list();

        Product read(Long productId);

        void remove(Long productId);

        Product modify(Long productId, ProductRequest productRequest);

}
