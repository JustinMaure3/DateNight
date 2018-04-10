package com.justinmaure.datenight;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinmaure.datenight.Objects.Date;

import java.util.ArrayList;

/**
 * Created by Max on 4/03/18.
 */

public class CustomAdapterPopDates extends RecyclerView.Adapter {
    private ArrayList<Date> dates;


    Context context;

    //RecyclerView for "Popular Dates"
    public CustomAdapterPopDates(ArrayList<Date> dates) {
        this.dates = dates;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pop_dates_recycler_view, parent, false);
        final CustomAdapterPopDates.CustomViewHolder viewHolder = new CustomAdapterPopDates.CustomViewHolder(view);
        context = parent.getContext();


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
        protected TextView rating;
        protected ImageView picture;
        protected ImageView isPublic;

        public CustomViewHolder(View view){
            super(view);
            this.dateName = (TextView) view.findViewById(R.id.dateName);
            this.description = (TextView) view.findViewById(R.id.description);
            this.picture = (ImageView) view.findViewById(R.id.picture);
            this.rating = (TextView) view.findViewById(R.id.rating);
            this.isPublic = (ImageView) view.findViewById(R.id.isPublic);

        }
    }
}
