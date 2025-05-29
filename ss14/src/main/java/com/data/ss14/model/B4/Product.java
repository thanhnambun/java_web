package com.data.ss14.model.B4;


import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Product {
    @NotBlank(message = "Product name is required")
    private String name;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;




}