package com.data.ss10.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    private String title;
    private String author;
    private String description;
    private MultipartFile file;
}
