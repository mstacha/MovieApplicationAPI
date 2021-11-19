package com.movieProjectAPI.Repository;

import com.movieProjectAPI.Model.Genre;
import com.movieProjectAPI.Model.Movie;
import com.movieProjectAPI.Model.Review;

import java.util.List;

public interface MovieRepository {
    Genre getGenreById(int id);
    Movie getMovieById(int id);
    Review getReviewById(String id);
    List<Movie> getSpecifiedNumberOfTopMovies(int number);
    List<Movie> getMoviesByTitle(String title);
    List<Movie> getMoviesByGenreName(String genreName);
    List<Review> getAllReviewsForMovie(int movieId);

}
