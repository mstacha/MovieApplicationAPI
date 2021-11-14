package com.movieProjectAPI.Repository;

import com.movieProjectAPI.Model.Actor;
import com.movieProjectAPI.Model.Genre;
import com.movieProjectAPI.Model.Movie;
import com.movieProjectAPI.Model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

@Repository
public class JdbcMovieRepository implements MovieRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Genre getGenreById(int id) {
        try{
            Genre genre = jdbcTemplate.queryForObject("SELECT * FROM public.\"Genre\" WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Genre.class), id);
            return genre;
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "genre not found"
            );
        }
    }

    public Movie getMovieById(int id) {
        try {
            Movie movie = jdbcTemplate.queryForObject("SELECT * FROM public.\"Movie\" WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Movie.class), id);
            return movie;
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "movie not found"
            );
        }
    }

    public Actor getActorById(int id) {
        try {
            Actor actor = jdbcTemplate.queryForObject("SELECT * FROM public.\"Actor\" WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Actor.class), id);
            return actor;
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "actor not found"
            );
        }
    }

    public Review getReviewById(int id) {
        try {
            Review review = jdbcTemplate.queryForObject("SELECT * FROM public.\"Review\" WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Review.class), id);
            return review;
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "review not found"
            );
        }
    }

    public List<Actor> getAllActorsFromMovie(int movieId){
        List<Actor> actors = jdbcTemplate.query("SELECT * FROM public.\"Actor\" WHERE public.\"Actor\".id IN (SELECT \"actorId\" FROM public.\"ActsIn\" WHERE \"movieId\"=" + movieId + ")",
                (rs, rowNum) ->
                new Actor(
                    rs.getInt("id"), rs.getString("name"), rs.getDate("birthdate"), rs.getBoolean("isMale")));
        return actors;
    }

    public List<Movie> getSpecifiedNumberOfTopMovies(int number){
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM public.\"Movie\" ORDER BY \"voteAverage\" DESC LIMIT " + number,
                (rs, rowNum) ->
                        new Movie(
                                rs.getInt("id"), rs.getString("originalLanguage"), rs.getString("title"),
                                rs.getString("overview"), rs.getDate("releaseDate"), rs.getInt("runtime"),
                                rs.getInt("budget"), rs.getInt("genreId"), rs.getString("posterPath"), rs.getDouble("voteAverage")));
        return movies;
    }

    public List<Movie> getSpecifiedNumberOfTopMoviesWithMinRating(double minRating, int number){
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM public.\"Movie\" WHERE \"voteAverage\" >= " + minRating + "ORDER BY \"voteAverage\" DESC LIMIT " + number,
                (rs, rowNum) ->
                        new Movie(
                                rs.getInt("id"), rs.getString("originalLanguage"), rs.getString("title"),
                                rs.getString("overview"), rs.getDate("releaseDate"), rs.getInt("runtime"),
                                rs.getInt("budget"), rs.getInt("genreId"), rs.getString("posterPath"), rs.getDouble("voteAverage")));
        return movies;
    }

    public List<Movie> getMoviesByTitle(String title){
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM public.\"Movie\" WHERE LOWER(title) SIMILAR TO '%" + title.toLowerCase() +"%' LIMIT 100",
                (rs, rowNum) ->
                        new Movie(
                                rs.getInt("id"), rs.getString("originalLanguage"), rs.getString("title"),
                                rs.getString("overview"), rs.getDate("releaseDate"), rs.getInt("runtime"),
                                rs.getInt("budget"), rs.getInt("genreId"), rs.getString("posterPath"), rs.getDouble("voteAverage")));
        return movies;
    }

    public List<Movie> getMoviesByGenreName(String genreName){
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM public.\"Movie\" WHERE \"genreId\" = (SELECT \"id\" FROM public.\"Genre\" WHERE LOWER(name) SIMILAR TO '%" + genreName.toLowerCase() + "%') LIMIT 100",
                (rs, rowNum) ->
                        new Movie(
                                rs.getInt("id"), rs.getString("originalLanguage"), rs.getString("title"),
                                rs.getString("overview"), rs.getDate("releaseDate"), rs.getInt("runtime"),
                                rs.getInt("budget"), rs.getInt("genreId"), rs.getString("posterPath"), rs.getDouble("voteAverage")));
        return movies;
    }

    public List<Review> getAllReviewsForMovie(int movieId){
        List<Review> reviews = jdbcTemplate.query("SELECT * FROM public.\"Review\" WHERE public.\"Review\".id =" + movieId +" LIMIT 100",
                (rs, rowNum) ->
                        new Review(
                                rs.getInt("id"), rs.getInt("movieId"), rs.getString("author"), rs.getString("content")));

        return reviews;
    }



}
