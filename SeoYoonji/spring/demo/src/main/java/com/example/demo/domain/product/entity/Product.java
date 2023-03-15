package com.example.demo.domain.product.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(length = 128, nullable = false)
    private String title;

    private Integer price;

    @Lob
    private String detail;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ProductImgs> productImgList = new ArrayList<>();

    @CreationTimestamp
    private Date regDate;

    public void setProductImgs (ProductImgs productImgs) {
        productImgList.add(productImgs);
        productImgs.setProduct(this);
    }

    public void setproductImgList (List<ProductImgs> productImgList) {
        productImgList.addAll(productImgList);

        for (int i = 0; i < productImgList.size(); i++) {
            productImgList.get(i).setProduct(this);
        }
    }
}
