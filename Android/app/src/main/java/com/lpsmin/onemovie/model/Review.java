package com.lpsmin.onemovie.model;

/**
 * Created by younes on 17/06/2017.
 */

public class Review {

    // PROPERTIES:

    private String id;
    private String author;
    private String content;
    private String iso_639_1;
    private Integer media_id;
    private String media_title;
    private String media_type;
    private String url;

    public Review(String id, String author, String content, String iso_639_1, Integer media_id, String media_title, String media_type, String url) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.iso_639_1 = iso_639_1;
        this.media_id = media_id;
        this.media_title = media_title;
        this.media_type = media_type;
        this.url = url;
    }

    // ACCESSORS AND MUTATORS:

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public Integer getMedia_id() {
        return media_id;
    }

    public void setMedia_id(Integer media_id) {
        this.media_id = media_id;
    }

    public String getMedia_title() {
        return media_title;
    }

    public void setMedia_title(String media_title) {
        this.media_title = media_title;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
