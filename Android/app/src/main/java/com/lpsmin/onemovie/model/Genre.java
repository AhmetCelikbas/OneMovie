package com.lpsmin.onemovie.model;

/**
 * Created by younes on 17/06/2017.
 */

public class Genre {

    // PROPERTIES:

    private Integer id;
    private String name;

    public Genre(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // ACCESSORS AND MUTATORS:

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
