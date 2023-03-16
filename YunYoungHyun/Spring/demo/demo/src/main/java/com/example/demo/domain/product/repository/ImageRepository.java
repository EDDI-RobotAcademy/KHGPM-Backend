package com.example.demo.domain.product.repository;

import com.example.demo.domain.product.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("select i from Image i join i.product p where p.productNo = :productNo")
    List<Image> findAllImagesByproductNo(@Param("productNo") Long productNo);

    void deleteByProductProductNo(Long productNo);

//    List<Image> findByProductNo(Long productNo);
}