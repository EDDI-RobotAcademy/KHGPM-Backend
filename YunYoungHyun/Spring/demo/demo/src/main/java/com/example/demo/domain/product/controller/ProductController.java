package com.example.demo.domain.product.controller;

import com.example.demo.domain.product.controller.dto.ProductResponse;
import com.example.demo.domain.product.controller.dto.RequestProductInfo;
import com.example.demo.domain.product.controller.dto.ProductRequest;
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
@CrossOrigin(origins = "http://localhost:8107", allowedHeaders = "*")
public class ProductController {

    final private ProductService productService;

//    @PostMapping("/register")
//    public void productRegister(@RequestBody ProductRequest productRequest) {
//        log.info("productRegister()");
//
//        System.out.println("ProductController 에서 보는: "+ productRequest);
//        productService.register(productRequest);
//    }

    @PostMapping(value = "/register",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ProductResponse productRegister(
            @RequestPart(value = "imageFileList", required = false) List<MultipartFile> imageFileList,
            @RequestPart(value = "productInfo") RequestProductInfo productRequest) {
        log.info("productRegister()");

        System.out.println("이거는 imageFileList: "+ imageFileList);
        System.out.println("이거는 내용: "+ productRequest);

        ProductResponse productResponse = productService.register(imageFileList, productRequest);
        return productResponse;
    }

    @GetMapping("/list")
    public List<ProductResponse> productList() {
        log.info("productList()");

        return productService.list();
    }

    @GetMapping("/{productNo}")
    public Product productRead(@PathVariable("productNo") Long productNo) {
        log.info("productRead()");
        System.out.println("컨트롤러에서 보는 productNo: "+ productNo);
        return productService.read(productNo);
    }

    @DeleteMapping("/{productNo}")
    public void productRemove(@PathVariable("productNo") Long productNo) {
        log.info("productModify()");

        productService.remove(productNo);
    }

    @PutMapping("/{productNo}")
    public Product productModify(@PathVariable("productNo") Long productNo, @RequestBody ProductRequest productRequest) {
        log.info("productModify()");

        return productService.modify(productNo, productRequest);
    }

}