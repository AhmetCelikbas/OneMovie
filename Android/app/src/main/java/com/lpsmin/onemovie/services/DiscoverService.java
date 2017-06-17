package com.lpsmin.onemovie.services;

import com.lpsmin.onemovie.enumerations.SortBy;
import com.lpsmin.onemovie.model.DiscoverFilter;
import com.lpsmin.onemovie.model.MovieResults;
import com.lpsmin.onemovie.model.TmdbDate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by younes on 16/06/2017.
 */

public interface DiscoverService {

    /**
     * Discover movies by different types of data
     * like average rating, number of votes, genres and certifications.
     */
    @GET("discover/movie")
    Call<MovieResults> discoverMovie(
        @Query("language") String language,
        @Query("region") String region,
        @Query("sort_by") SortBy sort_by,
        @Query("certification_country") String certification_country,
        @Query("certification") String certification,
        @Query("certification_lte") String certification_lte,
        @Query("include_adult") Boolean include_adult,
        @Query("include_video") Boolean include_video,
        @Query("page") Integer page,
        @Query("primary_release_year") Integer primary_release_year,
        @Query("primary_release_date.gte") TmdbDate primary_release_date_gte,
        @Query("primary_release_date.lte") TmdbDate primary_release_date_lte,
        @Query("release_date.gte") TmdbDate release_date_gte,
        @Query("release_date.lte") TmdbDate release_date_lte,
        @Query("vote_count.gte") Integer vote_count_gte,
        @Query("vote_count.lte") Integer vote_count_lte,
        @Query("vote_average.gte") Float vote_average_gte,
        @Query("vote_average.lte") Float vote_average_lte,
        @Query("with_cast") DiscoverFilter with_cast,
        @Query("with_crew") DiscoverFilter with_crew,
        @Query("with_companies") DiscoverFilter with_companies,
        @Query("with_genres") DiscoverFilter with_genres,
        @Query("with_keywords") DiscoverFilter with_keywords,
        @Query("with_people") DiscoverFilter with_people,
        @Query("year") Integer year,
        @Query("without_genres") DiscoverFilter without_genres,
        @Query("with_runtime.gte") Integer with_runtime_gte,
        @Query("with_runtime.lte") Integer with_runtime_lte,
        @Query("with_release_type") DiscoverFilter with_release_type,
        @Query("with_original_language") String with_original_language,
        @Query("without_keywords") String without_keywords
    );

}
