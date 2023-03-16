package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.dto.ProductRequest;
import com.example.demo.domain.product.controller.dto.ProductResponse;
import com.example.demo.domain.product.controller.dto.RequestProductInfo;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.entity.ProductImgs;
import com.example.demo.domain.product.repository.ProductImgsRepository;
import com.example.demo.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
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
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    final private ProductRepository productRepository;
    final private ProductImgsRepository productImgsRepository;

    @Override
    public void register(List<MultipartFile> productImgList, RequestProductInfo productRequest) {
        List<ProductImgs> imgList = new ArrayList<>();

        Product product = new Product();

        product.setTitle(productRequest.getTitle());
        product.setPrice(productRequest.getPrice());
        product.setDetail(productRequest.getDetail());

        final String fixedPath = "D:/proj/KHGPM-Frontend/SeoYoonji/src/assets/imgs/";
        try {
            for (MultipartFile multipartFile: productImgList) {
                log.info(multipartFile.getOriginalFilename());
                FileOutputStream writer = new FileOutputStream(
                        fixedPath + multipartFile.getOriginalFilename()
                );
                writer.write(multipartFile.getBytes());
                writer.close();

                ProductImgs productImgs = new ProductImgs(fixedPath + multipartFile.getOriginalFilename());
                imgList.add(productImgs);
                product.setProductImgs(productImgs);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        productRepository.save(product);

        for(ProductImgs productImgs: imgList) {
            productImgsRepository.save(productImgs);
        }

    }

    @Override
    public List<ProductResponse> list() {
        List<Product> pList = productRepository.findAll();
        List<ProductResponse> productList = new ArrayList<>();
        for(Product list: pList) {
            productList.add(new ProductResponse(list));
        }
        return productList;
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
