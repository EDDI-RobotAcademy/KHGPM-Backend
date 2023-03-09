package com.example.demo.domain.board.repository;

import com.example.demo.domain.board.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
