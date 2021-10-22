package com.movieProjectAPI.Repository;

import com.movieProjectAPI.Model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMovieRepository implements MovieRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Genre findById(int id) {
        Genre genre = jdbcTemplate.queryForObject("SELECT * FROM public.\"Genre\" WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Genre.class), id);
        return genre;
    }
}
