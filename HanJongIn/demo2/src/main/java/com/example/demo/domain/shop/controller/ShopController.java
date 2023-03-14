package com.example.demo.domain.shop.controller;

import com.example.demo.domain.fileTest.controller.request.RequestFileInfo;
import com.example.demo.domain.shop.controller.request.ShopRequest;
import com.example.demo.domain.shop.entity.Product;
import com.example.demo.domain.shop.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shop")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ShopController {

    final private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping(value = "/register", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public void shopRegister(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Integer price,
            @RequestPart(value = "fileList") List<MultipartFile> fileList) throws IOException {

        try {            for (MultipartFile multipartFile: fileList) {
                log.info("requestFileUploadWithText() - filename: " + multipartFile.getOriginalFilename());

                final String directory =

                        "../../../KHGPM-Frontend/JongInHan/frontend/src/assets/productImages/";

                FileOutputStream writer = new FileOutputStream( directory
                         +
                                multipartFile.getOriginalFilename()
                );

                writer.write(multipartFile.getBytes());
                writer.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("shopRegister()");

        ShopRequest shopRequest = new ShopRequest(name, description, price, fileList);

        shopService.register(shopRequest);
    }

    @GetMapping("/list")
    public List<Product> productList () {
        log.info("productList()");

        return shopService.list();
    }

}
