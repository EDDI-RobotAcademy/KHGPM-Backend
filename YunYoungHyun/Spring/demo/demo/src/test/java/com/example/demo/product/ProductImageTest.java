package com.example.demo.product;

import com.example.demo.domain.product.repository.ImageRepository;
import com.example.demo.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductImageTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void 게시물_저장() {

    }

//    @Test
//    public void 이미지_저장() {
//        Optional<Product> maybeProduct = productRepository.findById(1L);
//        Product product = maybeProduct.get();
//
//        Image image = new Image(2L, "img2.jpg");
//        product.setImage(image);
//
//        productRepository.save(product);
//        imageRepository.save(image);
//    }

//    @Test
//    public void 게시물_출력() {
//        List<Product> productList = productRepository.findAll();
//        List<ProductResponse> productResponses = new ArrayList<>();
//
//        for(Product product: productList) {
//            productResponses.add(new ProductResponse(
//                    product.getName(), product.getPrice(), product.getWriter(), product.getContent()
//            ));
//        }
//
//        System.out.println("게시물 출력: "+ productResponses);
//    }
    
//    @Test
//    public void 이미지_출력() {
//        List<Image> imageList = imageRepository.findAll();
//        List<ImageResponse> imageResponses = new ArrayList<>();
//
//        for(Image image: imageList) {
//            imageResponses.add(new ImageResponse(image.getImageName()));
//        }
//
//        System.out.println("게시물 출력: "+ imageResponses);
//    }

//    @Test
//    public void 상품_이미지_출력() {
//        List<Image> imageList = imageRepository.findAllImagesByproductNo(1L);
//        List<ImageResponse> imageResponses = new ArrayList<>();
//
//        for(Image image: imageList) {
//            imageResponses.add(new ImageResponse(image.getImageName()));
//        }
//        System.out.println("상품 이미지 출력: "+ imageResponses);
//    }

}