package com.example.demo.domain.shop.service;

import com.example.demo.domain.shop.controller.request.ShopRequest;
import com.example.demo.domain.shop.entity.Product;
import com.example.demo.domain.shop.repository.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ShopServiceImpl implements ShopService{
    final private ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public void register(ShopRequest shopRequest) {
        Product product = new Product();
        product.setName(shopRequest.getName());
        product.setDescription(shopRequest.getDescription());
        product.setPrice(shopRequest.getPrice());

        shopRepository.save(product);
    }

    @Override
    public List<Product> list() {
        return shopRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
    }
}
