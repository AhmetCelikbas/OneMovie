package com.lpsmin.onemovie.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpsmin.onemovie.R;
import com.lpsmin.onemovie.model.CastCredit;
import com.lpsmin.onemovie.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by younes on 22/06/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private Context context;
    public ArrayList<Movie> movies;

    // Constructor
    public SearchAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        public TextView searchItem;
        public SearchClickListener searchClickListener;

        public SearchViewHolder(final ViewGroup itemView) {
            super(itemView);
            searchItem = (TextView) itemView.findViewById(R.id.search_view);

            searchItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchClickListener.onSearchClick(v, getAdapterPosition());
                }
            });
        }
    }

    // Override methods:
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cast, null);
        return new SearchAdapter.SearchViewHolder(v);
    }
    @Override
    public void onBindViewHolder(SearchAdapter.SearchViewHolder viewHolder, int position) {
        Movie movie = movies.get(position);

        // Title
        viewHolder.searchItem.setText(movie.getTitle());
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // Click listener interface
    public interface SearchClickListener {
        void onSearchClick(View v, final int position);
    }
}
