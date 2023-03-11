package com.example.demo.domain.shop.controller;

import com.example.demo.domain.shop.controller.request.ShopRequest;
import com.example.demo.domain.shop.entity.Product;
import com.example.demo.domain.shop.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shop")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ShopController {

    final private ShopService shopService;


    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/register")
    public void shopRegister(@RequestBody ShopRequest shopRequest) {
        log.info("shopRegister()");

        shopService.register(shopRequest);
    }

    @GetMapping("/list")
    public List<Product> productList () {
        log.info("productList()");

        return shopService.list();
    }

}
