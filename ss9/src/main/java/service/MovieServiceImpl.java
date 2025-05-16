package service;

import model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import respository.MovieDAO;

import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDAO movieDAO;

    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }
}
