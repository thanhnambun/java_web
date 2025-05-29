package com.data.ss15.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String id;
    private String idProduct;
    private int rating;
    private String comment;
}
