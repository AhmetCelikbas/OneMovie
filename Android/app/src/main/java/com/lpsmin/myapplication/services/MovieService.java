package com.lpsmin.myapplication.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by younes on 16/06/2017.
 */

public class MovieService extends TmdbAPI {

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movie_id
     * @param language Optional. ISO 639-1 code.
     * @param appendToResponse Optional. extra requests to append to the result.
     */
    @GET("movie/{movie_id}")
    Call<Movie> getMovie(
            @Path("movie_id") int movie_id,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the reviews for a particular movie id.
     *
     * @param movie_id
     * @param page Optional. Minimum value is 1, expected value is an integer.
     * @param language Optional. ISO 639-1 code.
     */
    @GET("movie/{movie_id}/reviews")
    Call<ReviewResults> reviews(
            @Path("movie_id") int movie_id,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the cast and crew information for a specific movie id.
     *
     * @param movie_id
     */
    @GET("movie/{movie_id}/credits")
    Call<Credits> credits(
            @Path("movie_id") int movie_id
    );

    /**
     * Get the videos that have been added to a movie (e.g.: trailers, ...).
     *
     * @param movie_id
     * @param language Optional. ISO 639-1 code.
     */
    @GET("movie/{movie_id}/videos")
    Call<Videos> videos(
            @Path("movie_id") int movie_id,
            @Query("language") String language
    );

    /**
     * Get the list of upcoming movies.
     * This list refreshes every day.
     * The maximum number of items this list will include is 100.
     *
     * @param page Optional. Minimum value is 1, expected value is an integer.
     * @param language Optional. ISO 639-1 code.
     */
    @GET("movie/upcoming")
    Call<MovieResults> upcoming(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of movies playing in theaters.
     * This list refreshes every day.
     * The maximum number of items this list will include is 100.
     *
     * @param page Optional. Minimum value is 1, expected value is an integer.
     * @param language Optional. ISO 639-1 code.
     */
    @GET("movie/now_playing")
    Call<MovieResults> nowPlaying(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of popular movies on The Movie Database.
     * This list refreshes every day.
     *
     * @param page Optional. Minimum value is 1, expected value is an integer.
     * @param language Optional. ISO 639-1 code.
     */
    @GET("movie/popular")
    Call<MovieResults> popular(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of top rated movies.
     * By default, this list will only include movies that have 10 or more votes.
     * This list refreshes every day.
     *
     * @param page Optional. Minimum value is 1, expected value is an integer.
     * @param language Optional. ISO 639-1 code.
     */
    @GET("movie/top_rated")
    Call<MovieResults> topRated(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get a list of similar movies for a specific movie.
     *
     * @param movie_id
     * @param page Optional. Minimum value is 1, expected value is an integer.
     * @param language Optional. ISO 639-1 code.
     */
    @GET("movie/{movie_id}/similar")
    Call<MovieResults> similar(
            @Path("movie_id") int movie_id,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get a list of recommended movies for a movie.
     *
     * @param movie_id
     * @param page Optional. Minimum value is 1, expected value is an integer.
     * @param language Optional. ISO 639-1 code.
     */
    @GET("movie/{movie_id}/recommendations")
    Call<MovieResults> recommendations(
            @Path("movie_id") int movie_id,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the lists that the movie belongs to.
     *
     * @param movie_id
     * @param page Optional. Minimum value is 1, expected value is an integer.
     * @param language Optional. ISO 639-1 code.
     */
    @GET("movie/{movie_id}/lists")
    Call<ListResults> lists(
            @Path("movie_id") int movie_id,
            @Query("page") Integer page,
            @Query("language") String language
    );
}
