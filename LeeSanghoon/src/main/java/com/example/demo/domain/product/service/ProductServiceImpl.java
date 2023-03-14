package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.dto.ProductRequest;
import com.example.demo.domain.product.controller.dto.ProductResponse;
import com.example.demo.domain.product.controller.dto.RequestProductInfo;
import com.example.demo.domain.product.entity.ImageResource;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.repository.ImageResourceRepository;
import com.example.demo.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;
    final private ImageResourceRepository imageResourceRepository;

    /*
    @Override
    public void register(ProductRequest productRequest) {
        Product product = new Product();

        product.setProductName(productRequest.getProductName());
        product.setWriter(productRequest.getWriter());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);
    }
     */

    @Transactional
    @Override
    public void register(List<MultipartFile> fileList, RequestProductInfo productRequest) {
        log.info("글자 출력: " + productRequest);

        List<ImageResource> imageResourceList = new ArrayList<>();

        final String fixedStringPath = "../../KHGPM-Frontend/LeeSanghoon/frontend/src/assets/uploadImgs/";

        Product product = new Product();

        product.setProductName(productRequest.getProductName());
        product.setWriter(productRequest.getWriter());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());

        try {
            for (MultipartFile multipartFile : fileList) {
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
        List<ProductResponse> productResponseList = new ArrayList<>();

        for (Product product: productList) {
            productResponseList.add(new ProductResponse(
                    product.getProductId(), product.getProductName(),
                    product.getWriter(), product.getRegDate()
            ));
        }

        return productResponseList;
    }

    @Override
    public Product read(Long productId) {
        //Optional<Product> maybeProduct = productRepository.findById(productId);
        Optional<Product> maybeProduct = productRepository.findImagePathById(productId);

        if (maybeProduct.isEmpty()) {
            log.info("읽을 수가 없드아!");
            return null;
        }

        return maybeProduct.get();
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
