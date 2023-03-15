package com.example.demo.domain.product.controller;

import com.example.demo.domain.product.controller.dto.*;
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

    @PostMapping(value = "/register",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public void productRegister(
            // formData 형태로 받아온 상품 이미지파일 리스트와 상품 정보를 @RequestPart로 각각 받아옴
            @RequestPart(value = "fileList") List<MultipartFile> fileList,
            @RequestPart(value = "productInfo") RequestProductInfo productRequest) {
        log.info("productRegister()");

        // 받아온 상품 이미지파일 리스트와 상품 정보를 productService의 register 메서드의 매개변수로 넘겨줌
        productService.register(fileList, productRequest);
    }

    @GetMapping("/list")
    public List<ProductListResponse> productList () {
        log.info("boardList()");

        return productService.list();
    }

    @GetMapping("/{productId}")
    public ProductReadResponse productRead(@PathVariable("productId") Long productId) {
        log.info("productRead()");

        return productService.read(productId);
    }

    @DeleteMapping("/{productId}")
    public void productRemove(@PathVariable("productId") Long productId) {
        log.info("productRemove()");

        productService.remove(productId);
    }

    @PutMapping("/{productId}")
    public Product productModify(@PathVariable("productId") Long productId,
                                 @RequestBody ProductRequest productRequest) {

        log.info("productModify(): " + productRequest + "id: " + productId);

        return productService.modify(productId, productRequest);
    }

    // 이미지 경로를 리턴해주기 위함
    @GetMapping("/imageList/{productId}")
    public List<ImageResourceResponse> readProductImageResource(
            @PathVariable("productId") Long productId) {

        log.info("readProductImageResource(): " + productId);

        return productService.findProductImage(productId);
    }
}