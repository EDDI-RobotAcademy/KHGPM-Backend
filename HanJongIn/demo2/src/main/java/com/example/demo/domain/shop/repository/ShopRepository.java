package com.example.demo.domain.shop.repository;

import com.example.demo.domain.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Product, Long> {
}
