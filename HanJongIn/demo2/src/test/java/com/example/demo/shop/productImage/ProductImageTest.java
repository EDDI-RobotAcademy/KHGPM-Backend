package com.example.demo.shop.productImage;

import com.example.demo.domain.shop.controller.response.ImageDataResponse;
import com.example.demo.domain.shop.entity.ImageData;
import com.example.demo.domain.shop.entity.Product;
import com.example.demo.domain.shop.repository.ImageDataRepository;
import com.example.demo.domain.shop.repository.ShopRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductImageTest {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ImageDataRepository imageDataRepository;

    @Test
    public void 게시물_저장() {
        Product product = new Product();
        product.setName("test");
        product.setDescription("testcode");
        product.setPrice(1111);
        shopRepository.save(product);

        ImageData imageData = new ImageData();
        imageData.setImageData("테스트 경로입니다");
        product.addImageData(imageData);
        imageDataRepository.save(imageData);
    }

    @Test
    public void 제품별_사진_불러오기() {
        List<ImageData> imageDataList = imageDataRepository.findAllImagesByProductId(28L);
        List<ImageDataResponse> imageDataResponses = new ArrayList<>();

        for (ImageData imageData : imageDataList) {
            imageDataResponses.add(new ImageDataResponse((imageData.getImageData())));
        }

        System.out.println("imageDataResponses = " + imageDataResponses);
    }
}
