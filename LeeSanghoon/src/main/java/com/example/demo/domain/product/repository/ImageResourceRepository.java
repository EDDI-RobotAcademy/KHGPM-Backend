package com.example.demo.domain.product.repository;

import com.example.demo.domain.product.entity.ImageResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

import java.util.List;

public interface ImageResourceRepository extends JpaRepository<ImageResource, Long> {

    @Query("select ir from ImageResource ir join ir.product p where p.productId = :id")
    List<ImageResource> findImagePathByProductId(Long id);

    @Modifying
    @Transactional
    @Query("delete from ImageResource ir where ir.product.productId = :productId")
    void deleteSpecificProduct(@Param("productId") Long productId);
}
