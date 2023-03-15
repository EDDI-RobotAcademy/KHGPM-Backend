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
        List<Product> list = productRepository.findProduct();
        List<Product> products = new ArrayList<>();
        for (Object[] row : list) {
            Product product = new Product();
            product.setProductId((Long) row[0]);
            product.setProductName((String) row[1]);
            product.setWriter((String) row[2]);
            product.setContent((String) row[3]);
            product.setPrice((Integer) row[4]);
            product.setRegDate((Date) row[5]);
            product.setUpdDate((Date) row[6]);
            products.add(product);
        }
        
        System.out.println(Arrays.toString(list.toArray()));
    }
}
