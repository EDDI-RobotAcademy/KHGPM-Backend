package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.dto.ProductRequest;
import com.example.demo.domain.product.controller.dto.ProductResponse;
import com.example.demo.domain.product.controller.dto.RequestProductInfo;
import com.example.demo.domain.product.entity.ImageResource;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.repository.ImageResourceRepository;
import com.example.demo.domain.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;
    final private ImageResourceRepository imageResourceRepository;

    public  ProductServiceImpl(ProductRepository productRepository, ImageResourceRepository imageResourceRepository) {
        this.productRepository = productRepository;
        this.imageResourceRepository = imageResourceRepository;
    }

    @Override
    public void register(List<MultipartFile> imageFileList, RequestProductInfo productRequest) {
        log.info("글자 출력: " + productRequest);

        List<ImageResource> imageResourceList = new ArrayList<>();

        final String fixedStringPath = "../../../KHGPM-Frontend/ParkTaeHyeon/frontend/src/assets/uploadImgs/";


        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setWriter(productRequest.getWriter());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());

        try {
            for (MultipartFile multipartFile : imageFileList) {
                log.info("requestFileUploadWithText() - filename: " + multipartFile.getOriginalFilename());

                String fullPath = fixedStringPath + multipartFile.getOriginalFilename();

                FileOutputStream writer = new FileOutputStream(
                        fixedStringPath + multipartFile.getOriginalFilename()
                );

                writer.write(multipartFile.getBytes());
                writer.close();

                ImageResource imageResource = new ImageResource(fullPath);
                imageResourceList.add(imageResource);
                product.setImageResource(imageResource);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        productRepository.save(product);

        for (ImageResource imageResource: imageResourceList) {
            imageResourceRepository.save(imageResource);
        }
    }
    @Override
    public List<ProductResponse> list() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();

        for(Product product: productList) {
            ProductResponse productResponse = new ProductResponse(product);
            productResponses.add(productResponse);
        }

        // for-each 연습 좀 하자.

        return productResponses;
    }

    @Override
    public Product read(Long productId) {
        // 일 수도 있고 아닐 수도 있고
        Optional<Product> maybe = productRepository.findById(productId);

        if (maybe.isEmpty()) {
            log.info("읽을 수가 없드아!");
            return null;
        }

        return maybe.get();
    }
    @Override
    public void remove(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product modify(Long productId, ProductRequest productRequest) {
        Optional<Product> maybeProduct = productRepository.findById(productId);

        if (maybeProduct.isEmpty()) {
            System.out.println("Product 정보를 찾지 못했습니다: " + productId);
            return null;
        }

        Product product = maybeProduct.get();
        product.setProductName(productRequest.getProductName());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);

        return product;
    }
}