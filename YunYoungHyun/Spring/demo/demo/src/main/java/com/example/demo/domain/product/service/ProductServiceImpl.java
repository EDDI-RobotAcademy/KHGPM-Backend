package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.dto.ProductRequest;
import com.example.demo.domain.product.controller.dto.ProductResponse;
import com.example.demo.domain.product.controller.dto.RequestProductInfo;
import com.example.demo.domain.product.entity.Image;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.repository.ImageRepository;
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
    final private ImageRepository imageRepository;

//    @Autowired
//    public ProductServiceImpl(ProductRepository productRepository, ImageRepository imageRepository) {
//        this.productRepository = productRepository;
//        this.imageRepository = imageRepository;
//    }

    @Override
    public ProductResponse register(List<MultipartFile> imageFileList, RequestProductInfo productRequest) {
        log.info("글자 출력: " + productRequest);

        List<Image> imageList = new ArrayList<>();

        final String fixedStringPath = "D:/git/forLecture/KHGPM-Frontend/YunYoungHyun/vue/src/assets/uploadImgs/";

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setWriter(productRequest.getWriter());
        product.setContent(productRequest.getContent());

        try {
            for (MultipartFile multipartFile : imageFileList) {
                log.info("requestFileUploadWithText() - filename: " + multipartFile.getOriginalFilename());

                String fullPath = fixedStringPath + multipartFile.getOriginalFilename();
                FileOutputStream writer = new FileOutputStream(
                        fixedStringPath + multipartFile.getOriginalFilename()
                );
                writer.write(multipartFile.getBytes());
                writer.close();

                Image image = new Image(multipartFile.getOriginalFilename());
                imageList.add(image);

                // setImage(image) 하면 product 에 image 만 저장하는 게 아니고 image 에 product 도 저장한다.
                // 이게 무슨 말이냐? join 에 관한 얘기다.
                // 아래 코드만 보면 product 에 image 만 저장 하는 줄 알았더니?
                // setImage() 코드를 보니 image 에 product 도 저장해주네?
                product.setImage(image);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        productRepository.save(product);

        for (Image image : imageList) {
            imageRepository.save(image);
        }

        ProductResponse productResponse = new ProductResponse(product);
        return productResponse;
    }

//    @Override
//    public List<Image> findProductImage(Long productNo) {
//        return imageRepository.findByProductNo(productNo);
//    }

    @Override
    public List<ProductResponse> list() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();

        for(Product product: productList) {
            ProductResponse productResponse = new ProductResponse(product);
            productResponses.add(productResponse);
        }

        System.out.println("서비스에서 보는: "+ productResponses);
        return productResponses;
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
    @Transactional
    public void remove(Long productNo) {
        imageRepository.deleteByProductProductNo(productNo);
        productRepository.deleteByProductNo(productNo);
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