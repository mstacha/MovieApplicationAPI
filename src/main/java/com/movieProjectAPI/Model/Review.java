package com.movieProjectAPI.Model;

public class Review {
    private String id;
    private int movie_id;
    private String author;
    private String content;

    public Review() {}

    public Review(String id, int movie_id, String author, String content) {
        this.id = id;
        this.movie_id = movie_id;
        this.author = author;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMovieId() {
        return movie_id;
    }

    public void setMovieId(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
