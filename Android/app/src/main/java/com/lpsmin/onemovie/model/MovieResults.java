package com.lpsmin.onemovie.model;

import java.util.List;

/**
 * Created by younes on 17/06/2017.
 */

public class MovieResults extends ResultsPage {

    // PROPERTIES:

    private List<Movie> movies;


    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
