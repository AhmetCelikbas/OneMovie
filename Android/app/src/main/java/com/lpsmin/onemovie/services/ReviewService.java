package com.lpsmin.onemovie.services;

import com.lpsmin.onemovie.model.Review;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by younes on 16/06/2017.
 */

public interface ReviewService {

    /**
     * Get a review by id.
     *
     * @param review_id
     */
    @GET("review/{review_id}")
    Call<Review> getDetails(
        @Path("review_id") String review_id
    );

}
