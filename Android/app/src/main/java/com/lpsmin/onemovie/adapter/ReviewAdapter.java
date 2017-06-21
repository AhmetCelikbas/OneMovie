package com.lpsmin.onemovie.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lpsmin.onemovie.R;
import com.lpsmin.onemovie.model.CastCredit;
import com.lpsmin.onemovie.model.Review;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by younes on 21/06/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private Context context;
    public ArrayList<Review> reviews;

    // Constructor
    public ReviewAdapter(Context context, ArrayList<Review> castList) {
        this.context = context;
        this.reviews = reviews;
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout reviewItem;
        public TextView reviewAuthor;
        public TextView reviewContent;
        public TextView reviewDate;
        public ReviewClickListener reviewClickListener;

        public ReviewViewHolder(final ViewGroup itemView) {
            super(itemView);
            reviewAuthor = (TextView) itemView.findViewById(R.id.review_author);
            reviewContent = (TextView) itemView.findViewById(R.id.review_content);

            reviewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reviewClickListener.onReviewClick(v, getAdapterPosition());
                }
            });
        }
    }

    // Override methods:
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, null);
        return new ReviewAdapter.ReviewViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ReviewAdapter.ReviewViewHolder viewHolder, int position) {
        Review review = reviews.get(position);

        viewHolder.reviewAuthor.setText(review.getAuthor());
        viewHolder.reviewContent.setText(review.getContent());
    }
    @Override
    public int getItemCount() {
        return reviews.size();
    }

    // Click listener interface
    public interface ReviewClickListener {
        void onReviewClick(View v, final int position);
    }
}

