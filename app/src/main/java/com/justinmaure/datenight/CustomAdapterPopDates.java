package com.justinmaure.datenight;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.justinmaure.datenight.Objects.Date;
import com.justinmaure.datenight.Objects.User;

import java.util.ArrayList;

/**
 * Created by Max on 4/03/18.
 */

public class CustomAdapterPopDates extends RecyclerView.Adapter {
    private ArrayList<Date> dates;
    private ArrayList<User> users;

    Context context;

    private ImageView favButton;
    private RatingBar rating;


    //RecyclerView for "Popular Dates"
    public CustomAdapterPopDates(ArrayList<Date> dates) {
        this.dates = dates;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_dates_recycler_view, parent, false);

        final CustomAdapterPopDates.CustomViewHolder viewHolder = new CustomAdapterPopDates.CustomViewHolder(view);

        favButton = view.findViewById(R.id.favourited);

        context = parent.getContext();

        int location = viewHolder.getAdapterPosition();
        rating.setRating(dates.get(location).getRating());

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        favButton.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                int location = viewHolder.getAdapterPosition();

                User user = users.get(location);

                if (dates.get(location).getFavourited() == 1){
                    user.removeFromFavorites(dates.get(location));
                    viewHolder.favourited.setImageResource(R.drawable.ic_favorite_black_24dp);
                }else{
                    user.addToFavorites(dates.get(location));
                    viewHolder.favourited.setImageResource(R.drawable.ic_add_circle_black_24dp);
                }
            }
        });

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int location = viewHolder.getAdapterPosition();

                dates.get(location).setRating(rating.getRating());
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Date date = dates.get(position);
        ((CustomAdapterPopDates.CustomViewHolder) holder).dateName.setText(date.getDateName());
    }

    @Override
    public int getItemCount() {
        if(dates != null){
            return dates.size();
        }
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        protected TextView dateName;
        protected TextView description;
        protected RatingBar rating;
        protected ImageView picture;
        protected ImageView favourited;

        public CustomViewHolder(View view){
            super(view);
            this.dateName = (TextView) view.findViewById(R.id.dateName);
            this.description = (TextView) view.findViewById(R.id.description);
            this.picture = (ImageView) view.findViewById(R.id.picture);
            this.rating = (RatingBar) view.findViewById(R.id.rating);
            this.favourited = (ImageView) view.findViewById(R.id.favourited);
        }
    }
}
