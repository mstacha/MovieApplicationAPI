package com.movieProjectAPI.Repository;

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

@Repository
public class JdbcMovieRepository implements MovieRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Genre> getAllGenres(){
        List<Genre> genres = jdbcTemplate.query("SELECT * FROM public.\"genre\"",
                (rs, rowNum) ->
                        new Genre(
                                rs.getInt("id"), rs.getString("name")));
        return genres;    }

    public Genre getGenreById(int id) {
        try{
            Genre genre = jdbcTemplate.queryForObject("SELECT * FROM public.\"genre\" WHERE id=?",
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
            Movie movie = jdbcTemplate.queryForObject("SELECT * FROM public.\"movie\" WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Movie.class), id);
            return movie;
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "movie not found"
            );
        }
    }

    public Review getReviewById(String id) {
        try {
            Review review = jdbcTemplate.queryForObject("SELECT * FROM public.\"review\" WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Review.class), id);
            return review;
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "review not found"
            );
        }
    }



    public List<Movie> getSpecifiedNumberOfTopMovies(int number){
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM public.\"movie\" ORDER BY \"vote_average\" DESC LIMIT " + number,
                (rs, rowNum) ->
                        new Movie(
                                rs.getInt("id"), rs.getString("original_language"), rs.getString("title"),
                                rs.getString("overview"), rs.getDate("release_date"),
                                rs.getString("poster_path"), rs.getDouble("vote_average"), rs.getInt("genre_id")));
        return movies;
    }



    public List<Movie> getMoviesByTitle(String title){
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM public.\"movie\" WHERE LOWER(title) SIMILAR TO '%" + title.toLowerCase() +"%' LIMIT 100",
                (rs, rowNum) ->
                        new Movie(
                                rs.getInt("id"), rs.getString("original_language"), rs.getString("title"),
                                rs.getString("overview"), rs.getDate("release_date"),
                                rs.getString("poster_path"), rs.getDouble("vote_average"), rs.getInt("genre_id")));
        return movies;
    }

    public List<Movie> getMoviesByGenreName(String genreName){
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM public.\"movie\" WHERE \"genre_id\" = (SELECT \"id\" FROM public.\"genre\" WHERE LOWER(name) SIMILAR TO '%" + genreName.toLowerCase() + "%') LIMIT 100",
                (rs, rowNum) ->
                        new Movie(
                                rs.getInt("id"), rs.getString("original_language"), rs.getString("title"),
                                rs.getString("overview"), rs.getDate("release_date"),
                                rs.getString("poster_path"), rs.getDouble("vote_average"), rs.getInt("genre_id")));
        return movies;
    }

    public List<Review> getAllReviewsForMovie(int movieId){
        List<Review> reviews = jdbcTemplate.query("SELECT * FROM public.\"review\" WHERE public.\"review\".movie_id =" + movieId +" LIMIT 100",
                (rs, rowNum) ->
                        new Review(
                                rs.getString("id"), rs.getInt("movie_id"), rs.getString("author"), rs.getString("content")));

        return reviews;
    }



}
