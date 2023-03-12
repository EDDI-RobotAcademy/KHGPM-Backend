package com.example.demo.domain.shop.controller;

import com.example.demo.domain.shop.controller.request.ShopRequest;
import com.example.demo.domain.shop.entity.Product;
import com.example.demo.domain.shop.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public void shopRegister(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Integer price,
            @RequestParam("files") MultipartFile[] files) throws IOException {
        log.info("shopRegister()");

        ShopRequest shopRequest = new ShopRequest(name, description, price);
//        shopRequest.setName(name);
//        shopRequest.setDescription(description);
//        shopRequest.setPrice(price);

        shopService.register(shopRequest, files);
    }

    @GetMapping("/list")
    public List<Product> productList () {
        log.info("productList()");

        return shopService.list();
    }

}
