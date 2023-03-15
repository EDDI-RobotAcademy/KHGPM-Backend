package com.example.demo.domain.product.controller;

import com.example.demo.domain.product.controller.dto.ProductRequest;
import com.example.demo.domain.product.controller.dto.ProductResponse;
import com.example.demo.domain.product.controller.dto.RequestProductInfo;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ProductController {

    final private ProductService productService;


    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public void productRegister (@RequestPart(value = "productImgList")List<MultipartFile> productImgList,
                                 @RequestPart(value = "productInfo") RequestProductInfo productRequest) {
        log.info("productRegister()");
        productService.register(productImgList, productRequest);
    }

    @GetMapping("/list")
    public List<ProductResponse> productList () {
        return productService.list();
    }

    @GetMapping("/{productId}")
    public Product productRead(@PathVariable("productId") Long productId) {
        return productService.read(productId);
    }

    @DeleteMapping("/{productId}")
    public void productRemove(@PathVariable("productId") Long productId) {
        productService.remove(productId);
    }

    @PutMapping("/{productId}")
    public Product productModify(@PathVariable("productId") Long productId,
                                 @RequestBody ProductRequest productRequest) {
        return productService.modify(productId, productRequest);
    }
}
