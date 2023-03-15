package com.example.demo.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Data
@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productNo;

    @Column(length = 32, nullable = false)
    private String name;

    private Long price;

    @Column(length = 16, nullable = false)
    private String writer;

    @Lob
    private String content;

//    @Column(length = 10, nullable = false)
//    private Long views;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Image> imageList = new ArrayList<>();

    public void setImage(Image image) {
        imageList.add(image);
        image.setProduct(this);
    }

    public void setImageList (List<Image> imageList) {
        imageList.addAll(imageList);

        for (int i = 0; i < imageList.size(); i++) {
            imageList.get(i).setProduct(this);
        }
    }

}