package com.example.demo.domain.product.repository;

import com.example.demo.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p JOIN FETCH p.imageResourceList")
    List<Product> findAllWithImageResourceList();

    @Query("SELECT productId, productName, writer, content, price, regDate, updDate FROM Product")
    List<Product> findProduct();
}
