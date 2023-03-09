package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.ProductController;
import com.example.demo.domain.product.controller.request.ProductRequest;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    final private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void register(ProductRequest productRequest) {
        Product product = new Product();

        product.setTitle(productRequest.getTitle());
        product.setPrice(productRequest.getPrice());
        product.setDetail(productRequest.getDetail());

        productRepository.save(product);
    }

    @Override
    public List<Product> list() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
    }

    @Override
    public Product read(Long productId) {
        Optional<Product> maybeProduct = productRepository.findById(productId);

        if(maybeProduct.isEmpty()) {
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

        if(maybeProduct.isEmpty()) {
            return null;
        }

        Product product = maybeProduct.get();
        product.setTitle(productRequest.getTitle());
        product.setPrice(productRequest.getPrice());
        product.setDetail(productRequest.getDetail());

        productRepository.save(product);
        return product;
    }
}
