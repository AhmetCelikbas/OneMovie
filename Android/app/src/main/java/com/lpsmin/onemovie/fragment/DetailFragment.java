package com.lpsmin.onemovie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpsmin.onemovie.R;
import com.lpsmin.onemovie.adapter.CastAdapter;
import com.lpsmin.onemovie.adapter.MovieAdapter;
import com.lpsmin.onemovie.model.CastCredit;
import com.lpsmin.onemovie.model.Credit;
import com.lpsmin.onemovie.model.Credits;
import com.lpsmin.onemovie.model.Genre;
import com.lpsmin.onemovie.model.Movie;
import com.lpsmin.onemovie.model.MovieResults;
import com.lpsmin.onemovie.services.TmdbAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by younes on 17/06/2017.
 */

public class DetailFragment extends Fragment {
    private int movie_id;
    private CastAdapter adapter;
    private ArrayList<CastCredit> castList;

    ImageView movieImage;
    TextView overview;
    TextView rating;
    TextView date;
    TextView genres;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    // Override Methods:
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        movie_id = getArguments().getInt("movie_id");
        getActivity().setTitle("");

        movieImage = (ImageView) v.findViewById(R.id.detail_movie_image);
        overview = (TextView) v.findViewById(R.id.movie_overview);
        genres = (TextView) v.findViewById(R.id.detail_movie_genre);
        date = (TextView) v.findViewById(R.id.detail_movie_date);
        rating = (TextView) v.findViewById(R.id.detail_movie_rating);
        recyclerView = (RecyclerView) v.findViewById(R.id.cast_recycler_view);
/*
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter = new CastAdapter(getActivity(), castList);
        recyclerView.setAdapter(adapter);
*/
        getMovie(movie_id);

        return v;
    }

    public void getMovie(final int id) {
        Call<Movie> call = TmdbAPI.moviesService().getMovie(id, "fr", null);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    try {
                        Movie movie = response.body();

                        getActivity().setTitle(movie.getTitle());
                        overview.setText(movie.getOverview());
                        date.setText(movie.getRelease_date().toString());
                        rating.setText(movie.getVote_average().toString());

                        Picasso
                                .with(getContext())
                                .load("http://image.tmdb.org/t/p/w500/"+movie.getBackdrop_path())
                                .placeholder(R.drawable.default_movie_image)
                                .into(movieImage);

                        String genre = "";
                        for (int i = 0; i < movie.getGenres().size(); i++) {
                            if (i == 0) {
                                genre += movie.getGenres().get(i).getName();
                            } else {
                                genre += " / " + movie.getGenres().get(i).getName();
                            }
                        }
                        genres.setText(genre);
                        //getCast(id);
                    } catch(Exception e) {

                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    // Handle errors
                }
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                // something went completely south (like no internet connection)
            }
        });
    }

    public void getCast(int id) {
        Call<Credits> call = TmdbAPI.moviesService().credits(id);
        call.enqueue(new Callback<Credits>() {
            @Override
            public void onResponse(Call<Credits> call, Response<Credits> response) {
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    try {
                        List<CastCredit> result = response.body().getCast();
                        for (int i = 0; i < result.size(); i++) {
                            castList.add(result.get(i));
                        }
                        adapter.notifyDataSetChanged();
                    } catch(Exception e) {

                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    // Handle errors
                }
            }
            @Override
            public void onFailure(Call<Credits> call, Throwable t) {
                // something went completely south (like no internet connection)
            }
        });
    }

}
