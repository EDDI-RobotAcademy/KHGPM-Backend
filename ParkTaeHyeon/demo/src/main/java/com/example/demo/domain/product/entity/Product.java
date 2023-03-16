package com.example.demo.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ImageResource> imageResourceList = new ArrayList<>();
    // 관계를 맺은 두 엔티티가 서로 toString을 호출하면서
    // 무한 반복되어 StackOverFlow 에러가 발생하는 경우로 인하여
    // @JsonManagedReference로 직렬화 함

    //@ToString (exclude = "Product") 도 사용 가능 한 것으로 보여짐

    @Lob
    private String content;

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
