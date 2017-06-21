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

public final class TmdbAPI {

    // PROPERTIES:
    public static final String API_HOST = "api.themoviedb.org";
    public static final String API_VERSION = "3";
    public static final String API_URL = "https://" + API_HOST + "/" + API_VERSION + "/";
    public static final String API_KEY = "d8e80ec6dd23ba1687c149969994f760";
    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;


    // METHODS:

    protected static synchronized OkHttpClient okHttpClient() {
        if (okHttpClient == null) {
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
            okHttpClient = httpClient.build();
        }

        return okHttpClient;
    }

    protected static Retrofit getRetrofit() {
        if (retrofit == null) {
            GsonBuilder builder = new GsonBuilder()
                    .setDateFormat("dd/MM/yyyy");

            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .client(okHttpClient())
                    .build();
        }

        return retrofit;
    }

    public static MovieService moviesService() {
        return getRetrofit().create(MovieService.class);
    }

    public static PeopleService personService() {
        return getRetrofit().create(PeopleService.class);
    }

    public static SearchService searchService() {
        return getRetrofit().create(SearchService.class);
    }

    public static GenreService genreService() {
        return getRetrofit().create(GenreService.class);
    }

    public static ReviewService reviewsService() {
        return getRetrofit().create(ReviewService.class);
    }

    public static DiscoverService discoverService() {
        return getRetrofit().create(DiscoverService.class);
    }

}
