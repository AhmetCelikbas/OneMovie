package com.lpsmin.onemovie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpsmin.onemovie.R;
import com.lpsmin.onemovie.DetailActivity;
import com.lpsmin.onemovie.adapter.MovieAdapter;
import com.lpsmin.onemovie.model.Movie;
import com.lpsmin.onemovie.model.MovieResults;
import com.lpsmin.onemovie.services.TmdbAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by younes on 17/06/2017.
 */

public class MovieFragment extends Fragment implements MovieAdapter.MovieClickListener {
    ArrayList<Movie> movies = new ArrayList<Movie>();
    MovieAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    LinearLayoutManager LayoutManager;
    private String pNav;

    // Override Methods:
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie, container, false);

        pNav = getArguments().getString("param_nav");
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) v.findViewById(R.id.movie_recycler_view);

        recyclerView.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(LayoutManager);
        adapter = new MovieAdapter(getActivity(), movies);
        recyclerView.setAdapter(adapter);
        // set list item divider
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LayoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovies();
            }
        });
        getMovies();

        return v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Movie Frag");
    }
    @Override
    public void onMovieClick(View v, int position) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("id_movie", adapter.movieList.get(position).getId());
        startActivity(intent);
    }

    public void getMovies() {
        if (adapter == null) {
            adapter = new MovieAdapter(getActivity(), movies);
            recyclerView.setAdapter(adapter);
        }

        Call<MovieResults> call;
        switch (pNav) {
            case "in_theaters":
                call = TmdbAPI.moviesService().nowPlaying(1, "fr");
                break;
            case "popular":
                call = TmdbAPI.moviesService().popular(1, "fr");
                break;
            case "top_rated":
                call = TmdbAPI.moviesService().topRated(1, "fr");
                break;
            case "upcoming":
                call = TmdbAPI.moviesService().upcoming(1, "fr");
                break;
            default:
                call = TmdbAPI.moviesService().nowPlaying(1, "fr");
                break;
        }
        //call = TmdbAPI.moviesService().nowPlaying(1, "fr");

        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    try {
                        List<Movie> result = response.body().getResults();

                        for (int i = 0; i < result.size(); i++) {
                            movies.add(result.get(i));
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
            public void onFailure(Call<MovieResults> call, Throwable t) {
                // something went completely south (like no internet connection)
            }
        });
    }
}
