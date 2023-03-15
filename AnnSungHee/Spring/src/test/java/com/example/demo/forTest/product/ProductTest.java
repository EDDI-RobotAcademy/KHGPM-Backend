package com.example.demo.forTest.product;

import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.repository.ImageResourceRepository;
import com.example.demo.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageResourceRepository imageResourceRepository;

    @Test
    public void 리스트_가져오기() {
        List<Product> productList = productRepository.findProduct();
        for (Product product : productList) {
            System.out.println("productId: " + product.getProductId());
            System.out.println("productName: " + product.getProductName());
            System.out.println("writer: " + product.getWriter());
            System.out.println("content: " + product.getContent());
            System.out.println("price: " + product.getPrice());
            System.out.println("regDate: " + product.getRegDate());
            System.out.println("updDate: " + product.getUpdDate());
        }
    }
}
