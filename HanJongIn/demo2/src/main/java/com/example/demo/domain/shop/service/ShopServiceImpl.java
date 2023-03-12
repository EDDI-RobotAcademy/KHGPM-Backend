package com.example.demo.domain.shop.service;

import com.example.demo.domain.shop.controller.request.ShopRequest;
import com.example.demo.domain.shop.entity.ImageData;
import com.example.demo.domain.shop.entity.Product;
import com.example.demo.domain.shop.repository.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ShopServiceImpl implements ShopService{
    final private ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public void register(ShopRequest shopRequest, MultipartFile[] files) throws IOException {
        Product product = new Product();
        product.setName(shopRequest.getName());
        product.setDescription(shopRequest.getDescription());
        product.setPrice(shopRequest.getPrice());

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                byte[] fileData = file.getBytes();
                ImageData imageData = new ImageData();
                imageData.setData(fileData);
                product.addImageData(imageData);
            }
        }

        shopRepository.save(product);
    }

    @Override
    public List<Product> list() {
        return shopRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
    }
}
