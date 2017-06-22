package com.lpsmin.onemovie.db;

/**
 * Created by younes on 22/06/2017.
 */

public class DBMovie {
    private Integer id;
    private String title;

    public DBMovie(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
