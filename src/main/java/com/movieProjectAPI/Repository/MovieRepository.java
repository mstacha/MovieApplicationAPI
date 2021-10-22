package com.movieProjectAPI.Repository;

import com.movieProjectAPI.Model.Genre;

public interface MovieRepository {
    Genre findById(int id);
}
