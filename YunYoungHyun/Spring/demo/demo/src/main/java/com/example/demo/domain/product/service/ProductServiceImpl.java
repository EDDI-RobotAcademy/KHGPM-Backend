package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.request.ProductRequest;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public void register(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setWriter(productRequest.getWriter());
        product.setContent(productRequest.getContent());
        product.setViews(0L);

        productRepository.save(product);
    }

    @Override
    public List<Product> list() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "productNo"));
    }

    @Override
    public Product read(Long productNo) {
        // Optional : 일 수도 있고 아닐 수도 있고
        Optional<Product> maybeProduct = productRepository.findById(productNo);

        if(maybeProduct.isEmpty()) {
            log.info("읽을 수가 없드아!");
            return null;
        }
        System.out.println(maybeProduct);

        return maybeProduct.get();
    }

    @Override
    public void remove(Long productNo) {
        productRepository.deleteById(productNo);
    }

    @Override
    public Product modify(Long productNo, ProductRequest productRequest) {
        Optional<Product> maybeProduct = productRepository.findById(productNo);

        if(maybeProduct.isEmpty()) {
            System.out.println("Product 정보를 찾지 못했습니다: "+ productNo);
            return null;
        }

        Product product = maybeProduct.get();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setContent(productRequest.getContent());

        productRepository.save(product);

        return product;
    }


}