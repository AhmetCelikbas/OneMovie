package com.lpsmin.onemovie.model;

import java.util.List;

/**
 * Created by younes on 17/06/2017.
 */

public class Videos {
    private Integer id;
    private List<Video> results;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }
}
