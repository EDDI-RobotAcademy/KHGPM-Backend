package com.example.demo.domain.product.repository;

import com.example.demo.domain.product.entity.ProductImgs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImgsRepository extends JpaRepository<ProductImgs, Long> {
    @Query("select pi from ProductImgs pi join pi.product p where p.title = :title")
    List<ProductImgs> findImgListByTitle(String title);
}
