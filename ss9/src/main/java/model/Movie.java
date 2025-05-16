package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class  Movie {
    private long id;
    private String tile;
    private String director;
    private String genre;
    private String description;
    private int duration;
    private String language;
}
