package com.lpsmin.onemovie.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by younes on 17/06/2017.
 */

public class TmdbDate {
    private static final ThreadLocal<DateFormat> TMDB_DATE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        public DateFormat initialValue() {
            return new SimpleDateFormat("dd-MM-yyyy");
        }
    };

    private final Date date;

    public TmdbDate(Date date) {
        this.date = date;
    }

    public TmdbDate(String date) {
        Date parsedDate;
        try {
            parsedDate = TMDB_DATE_FORMAT.get().parse(date);
        } catch (ParseException e) {
            parsedDate = null;
        }
        this.date = parsedDate;
    }

    @Override
    public String toString() {
        return TMDB_DATE_FORMAT.get().format(date);
    }
}
