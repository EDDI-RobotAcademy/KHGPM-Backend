package com.example.demo.domain.product.controller;

import com.example.demo.domain.product.controller.dto.*;
import com.example.demo.domain.product.entity.ImageResource;
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
public class ProductController {

    final private ProductService productService;

    @PostMapping(value = "/register",
                consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public void productRegister(
            @RequestPart(value = "imageFileList") List<MultipartFile> imageFileList,
            @RequestPart(value = "productInfo") RequestProductInfo productRequest) {
        log.info("productRegister()");

        productService.register(imageFileList, productRequest);
    }

    @GetMapping("/list")
    public List<ProductListResponse> productList () {
        log.info("productList()");

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

    @GetMapping("/imageList/{productId}")
    public List<ImageResourceResponse> readProductImageResource(
            @PathVariable("productId") Long productId) {

        log.info("readProductImageResource(): " + productId);

        return productService.findProductImage(productId);
    }

    @GetMapping("/all")
    public List<AllProductResponse> allProductList () {
        log.info("allProductList()");

        return productService.all();
    }
}
