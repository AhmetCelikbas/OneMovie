package com.lpsmin.onemovie.model;

import java.util.Date;

/**
 * Created by younes on 17/06/2017.
 */

public class CastCredit extends Credit {
    private Integer cast_id;
    private String character;
    private Integer order;
    private String profile_path;

    public CastCredit(String credit_id, Integer id, String media_type, String name, Boolean adult, String original_title, String poster_path, Date release_date, String title, Integer cast_id, String character, Integer order, String profile_path) {
        super(credit_id, id, media_type, name,  adult, original_title, poster_path, release_date, title);
        this.cast_id = cast_id;
        this.character = character;
        this.order = order;
        this.profile_path = profile_path;
    }

    public Integer getCast_id() {
        return cast_id;
    }

    public void setCast_id(Integer cast_id) {
        this.cast_id = cast_id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
