package com.example.demo.domain.productBoard.service;

import com.example.demo.domain.productBoard.controller.request.ProductRequest;
import com.example.demo.domain.productBoard.controller.response.ProductResponse;
import com.example.demo.domain.productBoard.entity.ProductBoard;


import java.util.List;

public interface ProductBoardService {

    public void register(ProductRequest productRequest);

    List<ProductResponse> list();

}
