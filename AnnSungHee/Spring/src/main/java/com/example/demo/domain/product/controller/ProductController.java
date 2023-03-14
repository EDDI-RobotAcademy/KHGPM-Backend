package com.example.demo.domain.product.controller;

import com.example.demo.domain.product.controller.dto.ProductRequest;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ProductController {

    final private ProductService productService;

//    @PostMapping("/register")
//    public void productRegister(@RequestBody ProductRequest productRequest) {
//        log.info("productRegister()");
//
//        productService.register(productRequest);
//    }

    @PostMapping(value = "/register", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public void productRegister(@RequestBody ProductRequest productRequest,
                                @RequestPart(value = "file") List<MultipartFile> fileList) {
        String imgsrc = null;
        try {
            for (MultipartFile multipartFile : fileList) {
                log.info("requestFileUploadWithText() - filename: " + multipartFile.getOriginalFilename());

                // 사용자 계정 이름마다 별도로 사진을 배치하게 구성
                // 등록한 시간을 파일명 어딘가에 붙여서 파일을 저장
                imgsrc = "../../../KHGPM-Frontend/LeeSanghoon/frontend/src/assets/uploadImgs/" +
                        multipartFile.getOriginalFilename();
                FileOutputStream writer = new FileOutputStream(
                        "../../../KHGPM-Frontend/LeeSanghoon/frontend/src/assets/uploadImgs/" +
                                multipartFile.getOriginalFilename()
                );

                writer.write(multipartFile.getBytes());
                writer.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        productService.register(productRequest, imgsrc);
    }

    @GetMapping("/list")
    public List<Product> productList () {
        log.info("boardList()");

        return productService.list();
    }

    @GetMapping("/{productId}")
    public Product productRead(@PathVariable("productId") Long productId) {
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
}
