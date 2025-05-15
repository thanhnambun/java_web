package model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Question {
    private int id;
    private String imageUrl;
    private String answer;
}