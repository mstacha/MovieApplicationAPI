package com.movieProjectAPI.Repository;

import com.movieProjectAPI.Model.Actor;
import com.movieProjectAPI.Model.Genre;
import com.movieProjectAPI.Model.Movie;
import com.movieProjectAPI.Model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieRepository implements MovieRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Genre getGenreById(int id) {
        Genre genre = jdbcTemplate.queryForObject("SELECT * FROM public.\"Genre\" WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Genre.class), id);
        return genre;
    }

    public Movie getMovieById(int id) {
        Movie movie = jdbcTemplate.queryForObject("SELECT * FROM public.\"Movie\" WHERE id=?",
                BeanPropertyRowMapper.newInstance(Movie.class), id);
        return movie;
    }

    public Actor getActorById(int id) {
        Actor actor = jdbcTemplate.queryForObject("SELECT * FROM public.\"Actor\" WHERE id=?",
                BeanPropertyRowMapper.newInstance(Actor.class), id);
        return actor;
    }

    public Review getReviewById(int id) {
        Review review = jdbcTemplate.queryForObject("SELECT * FROM public.\"Review\" WHERE id=?",
                BeanPropertyRowMapper.newInstance(Review.class), id);
        return review;
    }

    public List<Actor> getAllActorsFromMovie(int movieId){
        List<Actor> actors = jdbcTemplate.query("SELECT * FROM public.\"Actor\" WHERE public.\"Actor\".id IN (SELECT \"actorId\" FROM public.\"ActsIn\" WHERE \"movieId\"=" + movieId + ")",
                (rs, rowNum) ->
                new Actor(
                    rs.getInt("id"), rs.getString("name"), rs.getDate("birthdate"), rs.getBoolean("isMale")));
        return actors;
    }



}
