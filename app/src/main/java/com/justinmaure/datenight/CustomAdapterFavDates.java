package com.justinmaure.datenight;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.justinmaure.datenight.Objects.Date;

import java.util.ArrayList;

/**
 * Created by Max on 4/03/18.
 */

public class CustomAdapterFavDates extends RecyclerView.Adapter {
    private ArrayList<Date> dates;


    Context context;

    //RecyclerView for "Favorite Dates"
    public CustomAdapterFavDates(ArrayList<Date> dates) {
        this.dates = dates;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_dates_recycler_view, parent, false);
        final CustomAdapterFavDates.CustomViewHolder viewHolder = new CustomAdapterFavDates.CustomViewHolder(view);
        context = parent.getContext();


        /**
         * This will trigger when the user taps the heart icon to favorite the date.
         * It should:
         * make the heart icon turn red
         * add the date to the user's favorite dates database
         *
         * otherwise, it should:
         * make the heart icon turn grey
         * remove the date from the user's favorite dates database
         */
        viewHolder.isFavourited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int location = viewHolder.getAdapterPosition();
                DatabaseHelper db = new DatabaseHelper(context);

                if (dates.get(location).getFavourited().equals(0)){
                    viewHolder.isFavourited.setImageResource(R.drawable.ic_favorite_black_24dp);
                    dates.get(location).setFavourited(1);
                    db.updateDate(dates.get(location));
                    MainActivity.currentUser.addToFavorites(dates.get(location));
                    db.close();
                }else {
                    viewHolder.isFavourited.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    dates.get(location).setFavourited(0);
                    db.updateDate(dates.get(location));
                    MainActivity.currentUser.removeFromFavorites(dates.get(location));
                    db.close();
                }
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Date date = dates.get(position);
        ((CustomAdapterFavDates.CustomViewHolder) holder).dateName.setText(date.getDateName());
        ((CustomAdapterFavDates.CustomViewHolder) holder).rating.setRating(date.getRating());
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
        protected ImageView isFavourited;

        public CustomViewHolder(View view){
            super(view);
            this.dateName = (TextView) view.findViewById(R.id.dateName);
            this.description = (TextView) view.findViewById(R.id.description);
            this.picture = (ImageView) view.findViewById(R.id.picture);
            this.rating = (RatingBar) view.findViewById(R.id.ratingBar);
            this.isFavourited = (ImageView) view.findViewById(R.id.favourited);

        }
    }
}
