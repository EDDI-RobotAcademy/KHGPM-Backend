package com.example.demo.domain.productBoard.service;

import com.example.demo.domain.board.repository.BoardRepository;
import com.example.demo.domain.productBoard.controller.request.ProductRequest;
import com.example.demo.domain.productBoard.entity.ProductBoard;
import com.example.demo.domain.productBoard.repository.ProductBoardRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBoardServiceImpl implements ProductBoardService {
    private final ProductBoardRepository productBoardRepository;


    public ProductBoardServiceImpl(ProductBoardRepository productBoardRepository) {
        this.productBoardRepository = productBoardRepository;
    }

    public List<ProductBoard> productBoardList(){

        return productBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
    }

    @Override
    public List<ProductBoard> list() {
        return null;
    }


}
