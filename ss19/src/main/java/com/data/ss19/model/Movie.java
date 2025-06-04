package com.data.ss19.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên phim không được để trống")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Đạo diễn không được để trống")
    @Column(nullable = false)
    private String director;

    @Min(value = 1900, message = "Năm phát hành phải từ 1900 trở lên")
    @Max(value = 2025, message = "Năm phát hành không được vượt quá năm hiện tại")
    private Integer releaseYear;

    private String genre;

    @Min(value = 1, message = "Thời lượng phim phải lớn hơn 0")
    private Integer duration;

    private String language;

    @Pattern(regexp = "^https?://.*", message = "URL poster phải hợp lệ")
    private String poster;

    @Column(nullable = false)
    private Boolean status = true;

    public Movie() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}