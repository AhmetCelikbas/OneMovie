package com.lpsmin.onemovie.model;

import java.util.List;

/**
 * Created by younes on 17/06/2017.
 */

public class ReviewResults extends ResultsPage {

    // PROPERTIES:

    private Integer id;
    private List<Review> results;


    // ACCESSORS AND MUTATORS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Review> getReviews() {
        return results;
    }

    public void setReviews(List<Review> reviews) {
        this.results = reviews;
    }
}
