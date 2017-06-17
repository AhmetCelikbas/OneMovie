package com.lpsmin.onemovie.services;

import com.lpsmin.onemovie.model.GenreResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by younes on 16/06/2017.
 */

public interface GenreService {

    /**
     * Get the list of movie genres.
     *
     * @param language Optional. ISO 639-1 code.
     */
    @GET("genre/movie/list")
    Call<GenreResults> getGenres(
        @Query("language") String language
    );

}
