package com.example.demo.domain.productBoard.service;

import com.example.demo.domain.productBoard.controller.request.ProductRequest;

import com.example.demo.domain.productBoard.controller.response.ProductResponse;
import com.example.demo.domain.productBoard.entity.ProductBoard;
import com.example.demo.domain.productBoard.repository.ProductBoardRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;

@Service
public class ProductBoardServiceImpl implements ProductBoardService {
    private final ProductBoardRepository productBoardRepository;
    //private final ProductResponse productResponse;


    public ProductBoardServiceImpl(ProductBoardRepository productBoardRepository) {
        this.productBoardRepository = productBoardRepository;
       // this.productResponse = productResponse;
    }


    @Override
    public List<ProductResponse> list() {

        List<ProductBoard> allData = productBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
        List<ProductResponse> productList = new ArrayList<>();

        for (ProductBoard product: allData){
            ProductResponse response = new ProductResponse(product.getProductId(), product.getTitle(), product.getWriter(),
                                                          product.getPrice(), product.getDelivery(), product.getRegDate());
            productList.add(response);
        }

        return productList;
    }


    public void register(ProductRequest productRequest){
        ProductBoard product = new ProductBoard();
        System.out.println("request 객체입니다");
        System.out.println(productRequest);
        product.setTitle(productRequest.getTitle());
        product.setWriter(productRequest.getWriter());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());
        product.setDelivery(productRequest.getDelivery());

        System.out.println("등록 service");

        productBoardRepository.save(product);
    }


}
