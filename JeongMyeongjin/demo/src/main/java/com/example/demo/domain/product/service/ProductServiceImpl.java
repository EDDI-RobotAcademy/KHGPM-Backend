package com.example.demo.domain.product.service;

import com.example.demo.domain.forTest.board.entity.Comment;
import com.example.demo.domain.product.controller.dto.*;
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

        // 현재 경로를 기준으로 프론트 엔드의 uploadImgs로 상대경로 값을 문자열로 저장함 (파일을 저장할 경로)
        final String fixedStringPath = "../../../KHGPM-Frontend/JeongMyeongjin/frontend/src/assets/uploadImgs/";

        Product product = new Product();

        // 받아온 상품정보 값 setting
        product.setProductName(productRequest.getProductName());
        product.setWriter(productRequest.getWriter());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());

        try {
            for (MultipartFile multipartFile: fileList) {
                log.info("requestFileUploadWithText() - filename: " + multipartFile.getOriginalFilename());

                // 파일 저장 위치에 파일 이름을 더해 fullPath 문자열 저장
                String fullPath = fixedStringPath + multipartFile.getOriginalFilename();


                FileOutputStream writer = new FileOutputStream(
                        fixedStringPath + multipartFile.getOriginalFilename()
                );

                writer.write(multipartFile.getBytes());
                writer.close();

                // 이미지 경로를 DB에 저장할때 경로를 제외한 이미지파일 이름만 저장하도록 함 (프론트에서 경로 지정하여 사용하기 위함)
                ImageResource imageResource = new ImageResource(multipartFile.getOriginalFilename());
                imageResourceList.add(imageResource);
                product.setImageResource(imageResource);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        productRepository.save(product);

        /*
        for (ImageResource imageResource: imageResourceList) {
            imageResourceRepository.save(imageResource);
        }
        */

        imageResourceRepository.saveAll(imageResourceList);
    }

    @Override
    public List<ProductListResponse> list() {
        // DB에서 모든 상품을 불러와 리스트에 저장
        List<Product> productList = productRepository.findAll();
        // 응답 파일 리스트를 응답할 리스트 생성
        List<ProductListResponse> productResponseList = new ArrayList<>();

        // 불러온 상품 리스트를 반복문을 통해 productResponseList에 추가
        for (Product product: productList) {
            productResponseList.add(new ProductListResponse(
                    product.getProductId(), product.getProductName(),
                    product.getWriter(), product.getRegDate()
            ));
        }

        // 추가한 productResponseList를 반환
        return productResponseList;
    }

    @Override
    public ProductReadResponse read(Long productId) {
        // 매개변수로 받아온 상품 아이디를 조건으로 DB에서 상품 정보를 불러와 maybeProduct에 저장
        Optional<Product> maybeProduct = productRepository.findById(productId);

        // maybeProduct 값이 비어있다면 null을 리턴
        if (maybeProduct.isEmpty()) {
            log.info("읽을 수가 없드아!");
            return null;
        }

        // 값이 있다면 product 객체에 값을 저장
        Product product = maybeProduct.get();

        // 상품 상세 정보를 Response 해줄 객체에 정보를 담음
        ProductReadResponse productReadResponse = new ProductReadResponse(
                product.getProductId(), product.getProductName(), product.getWriter(),
                product.getContent(), product.getPrice(), product.getRegDate()
        );

        // productReadResponse 응답
        return productReadResponse;
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

    @Override
    public List<ImageResourceResponse> findProductImage(Long productId) {
        List<ImageResource> imageResourceList = imageResourceRepository.findImagePathByProductId(productId);
        List<ImageResourceResponse> imageResourceResponseList = new ArrayList<>();

        for (ImageResource imageResource: imageResourceList) {
            System.out.println("imageResource path: " + imageResource.getImageResourcePath());

            imageResourceResponseList.add(new ImageResourceResponse(
                    imageResource.getImageResourcePath()));
        }

        return imageResourceResponseList;
    }
}