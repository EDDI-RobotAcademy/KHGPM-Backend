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

        productRepository.save(product);
    }

    @Override
    public List<Product> list() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
    }

    @Override
    public Product read(Long productId) {
        // 일 수도 있고 아닐 수도 있고
        Optional<Product> maybeproduct = productRepository.findById(productId);

        if (maybeproduct.isEmpty()) {
            log.info("읽을 수가 없드아!");
            return null;
        }

        return maybeproduct.get();
    }
    @Override
    public void remove(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product modify(Long productId, ProductRequest productRequest) {
        Optional<Product> maybeproduct = productRepository.findById(productId);

        if (maybeproduct.isEmpty()) {
            System.out.println("product 정보를 찾지 못했습니다: " + productId);
            return null;
        }

        Product product = maybeproduct.get();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);

        return product;
    }
}