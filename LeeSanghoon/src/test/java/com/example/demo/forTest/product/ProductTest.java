package com.example.demo.forTest.product;

import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductService productService;

    @Test
    public void 상품_읽기() {
        Product product = productService.read(1L);
        System.out.println("product read img path: " + product.getImageResourceList());
    }
}
