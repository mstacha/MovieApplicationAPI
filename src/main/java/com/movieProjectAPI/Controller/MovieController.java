package com.movieProjectAPI.Controller;

import com.movieProjectAPI.Model.Actor;
import com.movieProjectAPI.Model.Genre;
import com.movieProjectAPI.Model.Movie;
import com.movieProjectAPI.Model.Review;
import com.movieProjectAPI.Repository.JdbcMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class MovieController {

    @Autowired
    JdbcMovieRepository jdbcMovieRepository;

    @GetMapping("/genres/{id}")
    public Genre getGenreById(@PathVariable("id") int id) { return jdbcMovieRepository.getGenreById(id);}

    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable("id") int id) {
        return jdbcMovieRepository.getMovieById(id);
    }

    @GetMapping("/actors/{id}")
    public Actor getActorById(@PathVariable("id") int id) {
        return jdbcMovieRepository.getActorById(id);
    }

    @GetMapping("/reviews/{id}")
    public Review getReviewById(@PathVariable("id") int id) {
        return jdbcMovieRepository.getReviewById(id);
    }

    @GetMapping("/movies/{movieId}/actors")
    public List<Actor> getAllActorsFromMovie(@PathVariable("movieId") int movieId) { return jdbcMovieRepository.getAllActorsFromMovie(movieId); }




}
