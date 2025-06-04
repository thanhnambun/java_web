package com.data.ss19.controller;


import com.data.ss19.model.Movie;
import com.data.ss19.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public String listMovies(Model model) {
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        return "movies/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movies/form";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute("movie") @Valid Movie movie,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            return "movies/form";
        }
        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return "redirect:/movies";
        }
        model.addAttribute("movie", movie);
        return "movies/form";
    }

    @PostMapping("/edit/{id}")
    public String editMovie(@PathVariable Long id,
                            @ModelAttribute("movie") @Valid Movie movie,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            return "movies/form";
        }
        movie.setId(id);
        movieService.update(movie);
        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return "redirect:/movies";
    }
}