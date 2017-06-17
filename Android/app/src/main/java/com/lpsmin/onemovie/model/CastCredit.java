package com.lpsmin.onemovie.model;

/**
 * Created by younes on 17/06/2017.
 */

public class CastCredit extends Credit {
    private Integer cast_id;
    private String character;
    private Integer order;


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
}
