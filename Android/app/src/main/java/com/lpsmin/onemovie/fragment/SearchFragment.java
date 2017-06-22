package com.lpsmin.onemovie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpsmin.onemovie.DetailActivity;
import com.lpsmin.onemovie.R;
import com.lpsmin.onemovie.SearchActivity;
import com.lpsmin.onemovie.adapter.MovieAdapter;
import com.lpsmin.onemovie.adapter.SearchAdapter;
import com.lpsmin.onemovie.model.Movie;
import com.lpsmin.onemovie.model.MovieResults;
import com.lpsmin.onemovie.services.TmdbAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by younes on 22/06/2017.
 */

public class SearchFragment extends Fragment {
    private ArrayList<Movie> movies;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    SearchView mSearchView;
    TextView emptyView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        mSearchView = (SearchView) v.findViewById(R.id.search_view);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.search_recycler_view);
        emptyView = (TextView) v.findViewById(R.id.no_result);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        // set list item divider
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(itemDecoration);

        movies = new ArrayList<Movie>();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // do something on text submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // do something when text changes
                searchMovie(newText);

                return true;
            }
        });

        return v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Rechercher");
    }

    private void searchMovie(String query) {
        Call<MovieResults> call = TmdbAPI.searchService().searchMovie(query, 1, "fr", false, null, null, null);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    try {
                        mAdapter = new SearchAdapter(getActivity(), movies);
                        mRecyclerView.setAdapter(mAdapter);

                        List<Movie> result = response.body().getResults();

                        for (int i = 0; i < result.size(); i++) {
                            movies.add(result.get(i));
                        }
                        mAdapter.notifyDataSetChanged();
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
