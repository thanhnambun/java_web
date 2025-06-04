package com.data.ss18.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productStock;
    private String imageUrl;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductCart> productCartList;

}
