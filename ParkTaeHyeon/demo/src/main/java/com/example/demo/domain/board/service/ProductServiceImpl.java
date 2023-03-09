package com.example.demo.domain.board.service;

import com.example.demo.domain.board.controller.request.ProductRequest;
import com.example.demo.domain.board.entity.Product;
import com.example.demo.domain.board.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;

    public  ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void register(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setContent(productRequest.getContent());

        productRepository.save(product);
    }

    @Override
    public List<Product> list() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
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
}