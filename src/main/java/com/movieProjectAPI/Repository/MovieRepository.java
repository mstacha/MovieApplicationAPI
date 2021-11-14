package com.movieProjectAPI.Repository;

import com.movieProjectAPI.Model.Actor;
import com.movieProjectAPI.Model.Genre;
import com.movieProjectAPI.Model.Movie;
import com.movieProjectAPI.Model.Review;

import java.util.List;

public interface MovieRepository {
    Genre getGenreById(int id);
    Movie getMovieById(int id);
    Actor getActorById(int id);
    Review getReviewById(int id);
    List<Actor> getAllActorsFromMovie(int id);
    List<Movie> getSpecifiedNumberOfTopMovies(int number);
    List<Movie> getSpecifiedNumberOfTopMoviesWithMinRating(double minRating, int number);
    List<Movie> getMoviesByTitle(String title);
    List<Movie> getMoviesByGenreName(String genreName);
    List<Review> getAllReviewsForMovie(int movieId);
}
