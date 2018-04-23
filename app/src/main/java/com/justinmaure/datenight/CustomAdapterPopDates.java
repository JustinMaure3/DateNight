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

    Context context;

    ImageView favImage;

    //RecyclerView for "Popular Dates"
    public CustomAdapterPopDates(ArrayList<Date> dates) {
        this.dates = dates;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pop_dates_recycler_view, parent, false);

        final CustomViewHolder viewHolder = new CustomViewHolder(view);
        context = parent.getContext();

        int location = viewHolder.getAdapterPosition();


        viewHolder.favourited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int location = viewHolder.getAdapterPosition();
                DatabaseHelper db = new DatabaseHelper(context);

                if (dates.get(location).getFavourited().equals(0)){
                    viewHolder.favourited.setImageResource(R.drawable.ic_favorite_black_24dp);
                    dates.get(location).setFavourited(1);
                    db.updateDate(dates.get(location));
                    MainActivity.currentUser.addToFavorites(dates.get(location));
                    db.close();
                } else if (dates.get(location).getFavourited().equals(1)) {
                    viewHolder.favourited.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    dates.get(location).setFavourited(0);
                    db.updateDate(dates.get(location));
                    MainActivity.currentUser.removeFromFavorites(dates.get(location));
                    db.close();
                }
            }
        });

        viewHolder.rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int location = viewHolder.getAdapterPosition();
                DatabaseHelper db = new DatabaseHelper(context);

                dates.get(location).setRating(viewHolder.rating.getRating());
                db.updateDate(dates.get(location));
                db.close();
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Date date = dates.get(position);
        ((CustomAdapterPopDates.CustomViewHolder) holder).dateName.setText(date.getDateName());
        ((CustomViewHolder) holder).rating.setRating(date.getRating());
        if (dates.get(position).getFavourited().equals(1)) {
            ((CustomViewHolder) holder).favourited.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        else if (dates.get(position).getFavourited().equals(0)){
            ((CustomViewHolder) holder).favourited.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
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
            this.favourited = (ImageView) view.findViewById(R.id.popFavourited);
        }
    }
}
