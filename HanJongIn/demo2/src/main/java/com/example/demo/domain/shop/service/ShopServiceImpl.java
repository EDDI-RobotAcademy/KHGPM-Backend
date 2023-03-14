package com.example.demo.domain.shop.service;

import com.example.demo.domain.shop.controller.request.ShopRequest;
import com.example.demo.domain.shop.entity.ImageData;
import com.example.demo.domain.shop.entity.Product;
import com.example.demo.domain.shop.repository.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ShopServiceImpl implements ShopService{
    final private ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public String register(ShopRequest shopRequest) throws IOException {
        Product product = new Product();
        product.setName(shopRequest.getName());
        product.setDescription(shopRequest.getDescription());
        product.setPrice(shopRequest.getPrice());

        for (MultipartFile file : shopRequest.getFileList()) {
            if (!file.isEmpty()) {
                String filePath = "productImages/" + file.getOriginalFilename();

                ImageData imageData = new ImageData();
                imageData.setImageData(filePath);
                product.addImageData(imageData);

                FileOutputStream writer = new FileOutputStream("../../../KHGPM-Frontend/JongInHan/frontend/src/assets/" + filePath);
                writer.write(file.getBytes());
                writer.close();
            }
        }

        shopRepository.save(product);

        return "Upload Success!!!";
    }

    @Override
    public List<Product> list() {
        return shopRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
    }
}
