package com.example.demo.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(length = 120, nullable = false)
    private String productName;

    @Column(length = 20, nullable = false)
    private String kategorie;

    @Lob
    private String content;

    @Column(length = 20, nullable = false)
    private String brand;

    private Integer price;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ImageResource> imageResourceList = new ArrayList<>();

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