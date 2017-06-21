package com.lpsmin.onemovie.model;

import java.util.List;

/**
 * Created by younes on 17/06/2017.
 */

public class Collection {
    // PROPERTIES:
    private Integer id;
    private String backdrop_path;
    private String name;
    private String poster_path;
    private List<Parts> parts;
    private String overview;

    public Collection(Integer id, String backdrop_path, String name, String poster_path, List<Parts> parts, String overview) {
        this.id = id;
        this.backdrop_path = backdrop_path;
        this.name = name;
        this.poster_path = poster_path;
        this.parts = parts;
        this.overview = overview;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public List<Parts> getParts() {
        return parts;
    }

    public void setParts(List<Parts> parts) {
        this.parts = parts;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
