package com.example.demo.domain.product.controller;

import com.example.demo.domain.product.controller.request.ProductRequest;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ProductController {

    final private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/register")
    public void productRegister(@RequestBody ProductRequest productRequest) {
        log.info("productRegister()");

        System.out.println("ProductController 에서 보는: "+ productRequest);
        productService.register(productRequest);
    }

    @GetMapping("/list")
    public List<Product> productList() {
        log.info("productList()");

        return productService.list();
    }

    @GetMapping("/{productNo}")
    public Product productRead(@PathVariable("productNo") Long productNo) {
//        log.info("productRead()");
        System.out.println(productNo);
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