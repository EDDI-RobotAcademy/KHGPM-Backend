package com.example.demo.domain.product.repository;

import com.example.demo.domain.product.entity.ImageResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageResourceRepository extends JpaRepository<ImageResource, Long> {
}
