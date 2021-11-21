package com.movieProjectAPI.Controller;

import com.movieProjectAPI.Model.Actor;
import com.movieProjectAPI.Model.Genre;
import com.movieProjectAPI.Model.Movie;
import com.movieProjectAPI.Model.Review;
import com.movieProjectAPI.Repository.JdbcMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class MovieController {

    @Autowired
    JdbcMovieRepository jdbcMovieRepository;

    @GetMapping("/genres")
    public List<Genre> getGenreById() { return jdbcMovieRepository.getAllGenres();}

    @GetMapping("/genres/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Genre getGenreById(@PathVariable("id") int id) { return jdbcMovieRepository.getGenreById(id);}

    @GetMapping("/movies/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Movie getMovieById(@PathVariable("id") int id) {
        return jdbcMovieRepository.getMovieById(id);
    }

    @GetMapping("/actors/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Actor getActorById(@PathVariable("id") int id) {
        return jdbcMovieRepository.getActorById(id);
    }

    @GetMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Review getReviewById(@PathVariable("id") String id) {
        return jdbcMovieRepository.getReviewById(id);
    }

    @GetMapping("/movies/{movieId}/actors")
    public List<Actor> getAllActorsFromMovie(@PathVariable("movieId") int movieId) { return jdbcMovieRepository.getAllActorsFromMovie(movieId); }

    @GetMapping("/movies/topRated/maxResults/{number}")
    public List<Movie> getSpecifiedNumberOfTopMovies(@PathVariable("number") int number) {
        return jdbcMovieRepository.getSpecifiedNumberOfTopMovies(number);
    }


    @GetMapping("/movies/searchByTitle/{title}")
    public List<Movie> getMoviesByTitle(@PathVariable("title") String title) {
        return jdbcMovieRepository.getMoviesByTitle(title);
    }

    @GetMapping("/movies/searchByGenreName/{genreName}")
    public List<Movie> getMoviesByGenre(@PathVariable("genreName") String genreName) {
        return jdbcMovieRepository.getMoviesByGenreName(genreName);
    }

    @GetMapping("/movies/{id}/reviews")
    public List<Review> getAllReviewsForMovie(@PathVariable("id") int id) {
        return jdbcMovieRepository.getAllReviewsForMovie(id);
    }










}
