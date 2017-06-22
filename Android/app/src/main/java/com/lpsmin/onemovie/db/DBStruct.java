package com.lpsmin.onemovie.db;

/**
 * Created by younes on 22/06/2017.
 */


interface DBStruct {

    // Location table name
    String TABLE_FAVORITES = "favorites";

    // Generic id column
    String KEY_ID = "id";
    // Movie id column
    String KEY_MOVIE_ID = "movie_id";
    // Movie title column
    String KEY_MOVIE_TITLE = "movie_title";

    String CREATE_FAVORITES_TABLE = "" +
            "CREATE TABLE "+TABLE_FAVORITES+" (" +
            KEY_MOVIE_ID + " INTEGER PRIMARY KEY,"+
            KEY_MOVIE_TITLE + " TEXT)";
}