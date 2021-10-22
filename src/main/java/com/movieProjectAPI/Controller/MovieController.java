package com.movieProjectAPI.Controller;

import com.movieProjectAPI.Model.Genre;
import com.movieProjectAPI.Repository.JdbcMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class MovieController {

    @Autowired
    JdbcMovieRepository jdbcMovieRepository;

    @GetMapping("/genre/{id}")
    public Genre getGenreById(@PathVariable("id") int id) {
        return jdbcMovieRepository.findById(id);
    }
}
