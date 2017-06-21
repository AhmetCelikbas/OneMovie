package com.lpsmin.onemovie.model;

import java.util.Date;

/**
 * Created by younes on 17/06/2017.
 */

public class Credit {

    private String credit_id;
    private Integer id;
    private String media_type;
    private String name;

    private Boolean adult;
    private String original_title;
    private String poster_path;
    private Date release_date;
    private String title;

    public Credit(String credit_id, Integer id, String media_type, String name, Boolean adult, String original_title, String poster_path, Date release_date, String title) {
        this.credit_id = credit_id;
        this.id = id;
        this.media_type = media_type;
        this.name = name;
        this.adult = adult;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
