package com.data.ss11.controller.bt10;



import com.data.ss11.model.bt10.Movie;
import com.data.ss11.repository.MovieDAO;
import com.data.ss11.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String list(Model model) {
        List<Movie> movies = movieDAO.findAll();
        model.addAttribute("movies", movies);
        return "bt10/movie-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "bt10/movie-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("movie") @Valid Movie movie,
                      BindingResult result,
                      @RequestParam("posterFile") MultipartFile posterFile,
                      Model model) {
        if (result.hasErrors()) {
            return "bt10/movie-form";
        }
        try {
            if (!posterFile.isEmpty()) {
                String url = cloudinaryService.uploadFile(posterFile);
                movie.setPoster(url);
            }
            movieDAO.insert(movie);
        } catch (IOException e) {
            model.addAttribute("uploadError", "Lỗi upload ảnh!");
            return "bt10/movie-form";
        }
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Movie movie = movieDAO.findById(id);
        model.addAttribute("movie", movie);
        return "bt10/movie-form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @ModelAttribute("movie") @Valid Movie movie,
                       BindingResult result,
                       @RequestParam("posterFile") MultipartFile posterFile,
                       Model model) {
        if (result.hasErrors()) {
            return "bt10/movie-form";
        }
        try {
            if (!posterFile.isEmpty()) {
                String url = cloudinaryService.uploadFile(posterFile);
                movie.setPoster(url);
            } else {
                Movie old = movieDAO.findById(id);
                movie.setPoster(old.getPoster());
            }
            movie.setId(id);
            movieDAO.update(movie);
        } catch (IOException e) {
            model.addAttribute("uploadError", "Lỗi upload ảnh!");
            return "bt10/movie-form";
        }
        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        movieDAO.delete(id);
        return "redirect:/movies";
    }
}