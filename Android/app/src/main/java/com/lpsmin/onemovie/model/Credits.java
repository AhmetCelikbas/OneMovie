package com.lpsmin.onemovie.model;

import java.util.List;

/**
 * Created by younes on 17/06/2017.
 */

public class Credits {

    // PROPERTIES:

    private Integer id;
    private List<CastCredit> cast;
    private List<CrewCredit> crew;


    // ACCESSORS AND MUTATORS:

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CastCredit> getCast() {
        return cast;
    }

    public void setCast(List<CastCredit> cast) {
        this.cast = cast;
    }

    public List<CrewCredit> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewCredit> crew) {
        this.crew = crew;
    }
}
