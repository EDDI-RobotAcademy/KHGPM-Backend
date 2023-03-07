package com.example.demo.domain.product.controller;

import com.example.demo.domain.product.controller.request.ProductRequest;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
@RequiredArgsConstructor
public class ProductController {

    final private ProductService productService;

    @PostMapping("/register")
    public void productRegister(@RequestBody ProductRequest productRequest) {
        log.info("productRegister()");

        productService.register(productRequest);
    }

    @GetMapping("/list")
    public List<Product> productList() {
        log.info("productList()");

        return productService.list();
    }

    @GetMapping("/{productId}")
    public Product productRead(@PathVariable("productId") Long productId) {
        log.info("productRead()");

        return productService.read(productId);
    }

    @PutMapping("/{productId}")
    public Product productModify(@PathVariable("productId") Long productId,
                                 @RequestBody ProductRequest productRequest) {

        log.info("productModify(): " + productRequest + "id: " + productId);

        return productService.modify(productId, productRequest);
    }

    @DeleteMapping("/{productId}")
    public void productRemove(@PathVariable("productId") Long productId) {
        log.info("productRemove()");

        productService.remove(productId);
    }
}
