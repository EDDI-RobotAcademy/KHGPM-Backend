package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.controller.request.ProductRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.entity.Product;
import com.example.demo.domain.board.service.BoardService;
import com.example.demo.domain.board.service.ProductService;
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
    public void productRegister (@RequestBody ProductRequest productRequest) {
        log.info("productRegister()");

        productService.register(productRequest);
    }

    @GetMapping("/list")
    public List<Product> productList () {
        log.info("productList()");

        return productService.list();
    }

    @GetMapping("/{productId}")
    public Product productRead(@PathVariable("productId") Long productId) {
        log.info("productRead()");

        return productService.read(productId);
    }
}
