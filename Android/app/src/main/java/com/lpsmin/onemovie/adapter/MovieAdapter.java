package com.lpsmin.onemovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpsmin.onemovie.DetailActivity;
import com.lpsmin.onemovie.R;
import com.lpsmin.onemovie.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by younes on 17/06/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    public ArrayList<Movie> movieList;

    // Constructor
    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public CardView movieItem;
        public ImageView movieImage;
        public ImageView movieRatingIcon;
        public TextView movieName;
        public TextView movieDate;
        public TextView movieRating;
        //public TextView movieGenre;
        public MovieClickListener movieClickListener;

        public MovieViewHolder(final ViewGroup itemView) {
            super(itemView);
            movieItem = (CardView) itemView.findViewById(R.id.movie_item);
            movieImage = (ImageView) itemView.findViewById(R.id.movie_image);
            movieName = (TextView) itemView.findViewById(R.id.movie_name);
            movieDate = (TextView) itemView.findViewById(R.id.movie_date);
            movieRating = (TextView) itemView.findViewById(R.id.movie_rating);
            movieRatingIcon = (ImageView) itemView.findViewById(R.id.rating_icon);
            //movieGenre = (TextView) itemView.findViewById(R.id.movie_genre);

            movieItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieClickListener.onMovieClick(v, getAdapterPosition());
                }
            });
        }
    }

    // Override methods:
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, null);
        return new MovieAdapter.MovieViewHolder(v);
    }
    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder viewHolder, final int position) {
        Movie movie = movieList.get(position);

        // Title, year
        viewHolder.movieName.setText(movie.getTitle());
        viewHolder.movieDate.setText(movie.getRelease_date().toString());
        //viewHolder.movieGenre.setText(movie.getRelease_date().toString());
        // Load image
        if (TextUtils.isEmpty(movie.getBackdrop_path())) {
            viewHolder.movieImage.setImageResource(R.drawable.default_movie_image);
        } else {
            //url to get movie image : "http://image.tmdb.org/t/p/w185/"
            //image sizes : "w92", "w154", "w185", "w342", "w500", "w780", or "original"

            // GET IMAGE:
            Picasso
                .with(context)
                .load("http://image.tmdb.org/t/p/w342/"+movie.getPoster_path())
                .placeholder(R.drawable.default_movie_image)
                .into(viewHolder.movieImage);
        }
        // Display movie rating
        if (TextUtils.isEmpty(movie.getVote_average().toString()) || movie.getVote_average().equals("0")) {
            viewHolder.movieRatingIcon.setVisibility(View.GONE);
            viewHolder.movieRating.setVisibility(View.GONE);
        } else {
            viewHolder.movieRatingIcon.setVisibility(View.VISIBLE);
            viewHolder.movieRating.setVisibility(View.VISIBLE);
            viewHolder.movieRating.setText(movie.getVote_average().toString());
        }
        viewHolder.movieItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("movie_id", movieList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // Click listener interface
    public interface MovieClickListener {
        void onMovieClick(View v, final int position);
    }
}

