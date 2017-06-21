package com.lpsmin.onemovie.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpsmin.onemovie.R;
import com.lpsmin.onemovie.model.CastCredit;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by younes on 21/06/2017.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    private Context context;
    public ArrayList<CastCredit> castList;

    // Constructor
    public CastAdapter(Context context, ArrayList<CastCredit> castList) {
        this.context = context;
        this.castList = castList;
    }

    public static class CastViewHolder extends RecyclerView.ViewHolder {
        public CardView castItem;
        public CircleImageView castImage;
        public TextView castName;
        public TextView castRole;
        public CastClickListener castClickListener;

        public CastViewHolder(final ViewGroup itemView) {
            super(itemView);
            castItem = (CardView) itemView.findViewById(R.id.cast_item);
            castImage = (CircleImageView) itemView.findViewById(R.id.cImageView);
            castName = (TextView) itemView.findViewById(R.id.cast_name);
            castRole = (TextView) itemView.findViewById(R.id.cast_role);

            castItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    castClickListener.onCastClick(v, getAdapterPosition());
                }
            });
        }
    }

    // Override methods:
    @Override
    public CastAdapter.CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cast, null);
        return new CastAdapter.CastViewHolder(v);
    }
    @Override
    public void onBindViewHolder(CastAdapter.CastViewHolder viewHolder, int position) {
        CastCredit cast = castList.get(position);

        // Title, year
        viewHolder.castName.setText(cast.getName());
        viewHolder.castRole.setText(cast.getCharacter());
        // Load image
        if (TextUtils.isEmpty(cast.getProfile_path())) {
            viewHolder.castImage.setImageResource(R.drawable.default_movie_image);
        } else {
            //url to get movie image : "http://image.tmdb.org/t/p/w185/"
            //image sizes : "w92", "w154", "w185", "w342", "w500", "w780", or "original"

            // GET IMAGE:
            Picasso
                    .with(context)
                    .load("http://image.tmdb.org/t/p/w342/"+cast.getProfile_path())
                    .placeholder(R.drawable.default_movie_image)
                    .into(viewHolder.castImage);
        }
    }
    @Override
    public int getItemCount() {
        return castList.size();
    }

    // Click listener interface
    public interface CastClickListener {
        void onCastClick(View v, final int position);
    }
}

