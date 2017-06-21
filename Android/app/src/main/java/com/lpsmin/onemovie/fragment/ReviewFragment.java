package com.lpsmin.onemovie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpsmin.onemovie.R;
import com.lpsmin.onemovie.adapter.CastAdapter;
import com.lpsmin.onemovie.adapter.ReviewAdapter;
import com.lpsmin.onemovie.model.CastCredit;
import com.lpsmin.onemovie.model.Movie;
import com.lpsmin.onemovie.model.MovieResults;
import com.lpsmin.onemovie.model.Review;
import com.lpsmin.onemovie.model.ReviewResults;
import com.lpsmin.onemovie.services.TmdbAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by younes on 21/06/2017.
 */

public class ReviewFragment extends Fragment {
    private int movie_id;

    private ReviewAdapter adapter;
    private ArrayList<Review> reviewList;

    TextView author;
    TextView content;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_review, container, false);

        movie_id = getArguments().getInt("movie_id");
        getMovie(movie_id);

        recyclerView = (RecyclerView) v.findViewById(R.id.review_list);
        author = (TextView) v.findViewById(R.id.review_author);
        content = (TextView) v.findViewById(R.id.review_content);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter = new ReviewAdapter(getActivity(), reviewList);
        recyclerView.setAdapter(adapter);

        return v;
    }

    public void getMovie(int id) {
        Call<ReviewResults> call = TmdbAPI.moviesService().reviews(id, 1, "fr");
        call.enqueue(new Callback<ReviewResults>() {
            @Override
            public void onResponse(Call<ReviewResults> call, Response<ReviewResults> response) {
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    try {
                        List<Review> result = response.body().getReviews();
                        for (int i = 0; i < result.size(); i++) {
                            reviewList.add(result.get(i));
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
            public void onFailure(Call<ReviewResults> call, Throwable t) {
                // something went completely south (like no internet connection)
            }
        });
    }
}
