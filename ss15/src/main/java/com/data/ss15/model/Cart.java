package com.data.ss15.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private String idCart;
    private String idProduct;
    private int quantity;
    private double subtotal;
}
