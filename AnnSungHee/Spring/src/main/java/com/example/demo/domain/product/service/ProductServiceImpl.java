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
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "boardId"));
    }

    @Override
    public Product read(Long boardId) {
        // 일 수도 있고 아닐 수도 있고
        Optional<Product> maybeBoard = productRepository.findById(boardId);

        if (maybeBoard.isEmpty()) {
            log.info("읽을 수가 없드아!");
            return null;
        }

        return maybeBoard.get();
    }
    @Override
    public void remove(Long boardId) {
        productRepository.deleteById(boardId);
    }

    @Override
    public Product modify(Long boardId, ProductRequest productRequest) {
        Optional<Product> maybeBoard = productRepository.findById(boardId);

        if (maybeBoard.isEmpty()) {
            System.out.println("Board 정보를 찾지 못했습니다: " + boardId);
            return null;
        }

        Product product = maybeBoard.get();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);

        return product;
    }
}