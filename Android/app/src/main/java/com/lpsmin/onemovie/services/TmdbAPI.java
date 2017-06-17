package com.lpsmin.onemovie.services;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by younes on 16/06/2017.
 */

public class TmdbAPI {

    // PROPERTIES:

    public static final String API_HOST = "api.themoviedb.org";
    public static final String API_VERSION = "3";
    public static final String API_URL = "https://" + API_HOST + "/" + API_VERSION + "/";
    public static final String API_KEY = "d8e80ec6dd23ba1687c149969994f760";

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;


    // METHODS:

    protected synchronized OkHttpClient okHttpClient() {
        if (this.okHttpClient() == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    HttpUrl url = chain.request().url()
                            .newBuilder()
                            .addQueryParameter("api_key", API_KEY)
                            .build();

                    // Request customization: add request headers
                    Request request = chain.request().newBuilder().url(url).build();
                    return chain.proceed(request);
                }
            });
            this.okHttpClient = httpClient.build();
        }

        return this.okHttpClient;
    }

    protected Retrofit getRetrofit() {
        if (this.retrofit == null) {
            GsonBuilder builder = new GsonBuilder()
                    .setDateFormat("dd/MM/yyyy");

            this.retrofit = new Retrofit.Builder()
                    .baseUrl(this.API_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .client(this.okHttpClient())
                    .build();
        }

        return this.retrofit;
    }

    public MovieService moviesService() {
        return this.getRetrofit().create(MovieService.class);
    }

    public PeopleService personService() {
        return this.getRetrofit().create(PeopleService.class);
    }

    public SearchService searchService() {
        return this.getRetrofit().create(SearchService.class);
    }

    public GenreService genreService() {
        return this.getRetrofit().create(GenreService.class);
    }

    public ReviewService reviewsService() {
        return this.getRetrofit().create(ReviewService.class);
    }

    public DiscoverService discoverService() {
        return this.getRetrofit().create(DiscoverService.class);
    }

}
