package com.lpsmin.myapplication.services;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by younes on 16/06/2017.
 */

public class PeopleService extends TmdbAPI {

    /**
     * Get the primary person details by id.
     *
     * @param person_id
     * @param language Optional. ISO 639-1 code.
     * @param append_to_response Optional. extra requests to append to the result.
     */
    @GET("person/{person_id}")
    Call<Person> summary(
        @Path("person_id") int person_id,
        @Query("language") String language,
        @Query("append_to_response") AppendToResponse append_to_response
    );

}
