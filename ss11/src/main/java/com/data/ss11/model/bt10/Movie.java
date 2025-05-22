package com.data.ss11.model.bt10;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class Movie {
    private Integer id;

    @NotBlank(message = "Tên phim không được để trống")
    @Size(max = 100, message = "Tên phim tối đa 100 ký tự")
    private String title;

    @NotBlank(message = "Đạo diễn không được để trống")
    @Size(max = 50, message = "Đạo diễn tối đa 50 ký tự")
    private String director;

    @NotNull(message = "Ngày phát hành không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @NotBlank(message = "Thể loại không được để trống")
    @Size(max = 30, message = "Thể loại tối đa 30 ký tự")
    private String genre;

    private String poster;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public String getPoster() {
        return poster;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}