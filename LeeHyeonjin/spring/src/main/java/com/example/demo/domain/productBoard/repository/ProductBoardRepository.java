package com.example.demo.domain.productBoard.repository;


import com.example.demo.domain.productBoard.entity.ProductBoard;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductBoardRepository extends JpaRepository<ProductBoard, Long> {


}
