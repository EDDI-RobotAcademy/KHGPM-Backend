package com.example.demo.domain.productBoard.controller;

import com.example.demo.domain.productBoard.controller.request.ProductRequest;
import com.example.demo.domain.productBoard.controller.response.ProductResponse;
import com.example.demo.domain.productBoard.entity.ProductBoard;
import com.example.demo.domain.productBoard.service.ProductBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ProductBoardController {
    private final ProductBoardService productBoardService;

    @GetMapping("/list")
    public List<ProductResponse> productBoardList() {

        return productBoardService.list();
    }

    @PostMapping("/add")
    public void productBoardRegister (@RequestBody ProductRequest productRequest){

        productBoardService.register(productRequest);
    }

}
