package com.lpsmin.onemovie.services;

import com.lpsmin.onemovie.model.MovieResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by younes on 16/06/2017.
 */

public interface SearchService {

    /**
     * Search for movies.
     *
     * @param query CGI escaped string
     * @param page Optional. Minimum value is 1, expected value is an integer.
     * @param language Optional. ISO 639-1 code.
     * @param includeAdult Optional. Toggle the inclusion of adult titles. Expected value is: true or false
     * @param year Optional. Filter the results release dates to matches that include this value.
     * @param primaryReleaseYear Optional. Filter the results so that only the primary release dates have this
     * value.
     * @param searchType Optional. By default, the search type is 'phrase'. This is almost guaranteed the
     * option you will want. It's a great all purpose search type and by far the most tuned for every day querying. For
     * those wanting more of an "autocomplete" type search, set this option to 'ngram'.
     */
    @GET("search/movie")
    Call<MovieResults> searchMovie(
        @Query("query") String query,
        @Query("page") Integer page,
        @Query("language") String language,
        @Query("include_adult") Boolean includeAdult,
        @Query("year") Integer year,
        @Query("primary_release_year") Integer primaryReleaseYear,
        @Query("search_type") String searchType
    );

}
