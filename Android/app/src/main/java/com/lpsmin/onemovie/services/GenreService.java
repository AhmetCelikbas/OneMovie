package com.lpsmin.onemovie.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by younes on 16/06/2017.
 */

public class GenreService extends TmdbAPI {

    /**
     * Get the list of movie genres.
     *
     * @param language Optional. ISO 639-1 code.
     */
    @GET("genre/movie/list")
    Call<GenreList> getGenres(
        @Query("language") String language
    );

}
