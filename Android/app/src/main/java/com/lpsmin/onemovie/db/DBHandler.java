package com.lpsmin.onemovie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lpsmin.onemovie.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by younes on 22/06/2017.
 */

public class DBHandler implements DBStruct {

    private DBHelper mDbHelper;
    private SQLiteDatabase mDb;

    private final Context mCtx;

    public DBHandler(Context mCtx) {
        this.mCtx = mCtx;
    }

    /**
     * Method to
     */
    public void open() {
        mDbHelper = new DBHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
    }

    public void add(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(KEY_MOVIE_ID, movie.getId());
        values.put(KEY_MOVIE_TITLE, movie.getTitle());
        mDb.insert(TABLE_FAVORITES, null, values);
    }

    public List<DBMovie> getAllObj() {

        String[] columns = new String[] {
                KEY_ID,
                KEY_MOVIE_TITLE
        };
        Cursor cursor = mDb.query(TABLE_FAVORITES, columns, "1", null, null, null, null);
        int iPostId = cursor.getColumnIndex(KEY_MOVIE_ID);
        int iPostMovie = cursor.getColumnIndex(KEY_MOVIE_TITLE);

        List<DBMovie> movies = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                DBMovie movie= new DBMovie(cursor.getInt(iPostId), cursor.getString(iPostMovie));
                movies.add(movie);
            } while(cursor.moveToNext());
        }

        return movies;
    }

    public void removeObj(Movie mObj) {
        mDb.delete(TABLE_FAVORITES, KEY_ID + "= ?", new String[]{String.valueOf(mObj.getId())});
    }

    public void close() {
        mDbHelper.close();
    }
}
