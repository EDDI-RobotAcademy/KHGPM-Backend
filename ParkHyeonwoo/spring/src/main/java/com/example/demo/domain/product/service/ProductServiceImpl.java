package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.request.ProductRequest;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;

    @Override
    public void register(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setWriter(productRequest.getWriter());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);
    }

    @Override
    public List<Product> list() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
    }

    public Product read(Long productId) {
        Optional<Product> maybeProduct = productRepository.findById(productId);

        if(maybeProduct.isEmpty()) {
            log.info("읽을 수가 없음");
            return null;
        }

        return maybeProduct.get();
    }

    @Override
    public Product modify(Long productId, ProductRequest productRequest) {
        Optional<Product> maybeProduct = productRepository.findById(productId);

        if(maybeProduct.isEmpty()) {
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
    public void remove(Long productId) {
        productRepository.deleteById(productId);
    }

}
