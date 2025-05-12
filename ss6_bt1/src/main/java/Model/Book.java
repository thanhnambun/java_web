package Model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private String bookCode;
    private String title;
    private String author;
    private String category;
    private int quantity;}
