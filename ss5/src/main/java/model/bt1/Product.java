package model.bt1;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
}
