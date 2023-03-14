package com.example.demo.domain.product.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private String productName;

    @Column(length = 32, nullable = false)
    private String writer;

    @Lob
    private String content;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ImageResource> imageResourceList = new ArrayList<>();

    private Integer price;

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date updDate;

    public void setImageResource (ImageResource imageResource) {
        imageResourceList.add(imageResource);
        imageResource.setProduct(this);
    }

    public void setImageResourceList (List<ImageResource> imageResourceList) {
        imageResourceList.addAll(imageResourceList);

        for (int i = 0; i < imageResourceList.size(); i++) {
            imageResourceList.get(i).setProduct(this);
        }
    }
}
