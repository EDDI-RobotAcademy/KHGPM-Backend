package com.example.demo.domain.shop.service;

import com.example.demo.domain.shop.controller.request.ShopRequest;
import com.example.demo.domain.shop.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShopService {
    public void register(ShopRequest shopRequest);

    List<Product> list();
}
