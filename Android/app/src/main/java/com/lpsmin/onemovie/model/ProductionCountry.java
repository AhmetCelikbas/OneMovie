package com.lpsmin.onemovie.model;

/**
 * Created by younes on 17/06/2017.
 */

public class ProductionCountry {
    // PROPERTIES:
    private String iso_3166_1;
    private String name;

    public ProductionCountry(String iso_3166_1, String name) {
        this.iso_3166_1 = iso_3166_1;
        this.name = name;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
