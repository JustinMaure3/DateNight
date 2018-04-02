package com.justinmaure.datenight;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Max on 3/31/18.
 */

public class CustomAdapter extends RecyclerView.Adapter {
    private ArrayList<Date> popularDates;
    private ArrayList<FavouriteDates> favouriteDates;
    private ArrayList<MyDates> myDates;

    Context context;

    //RecyclerView for most popular dates
    public CustomAdapter(ArrayList<PopularDates> popularDates) {
        this.popularDates = popularDates;
    }

    //RecyclerView for favourite dates
    public CustomAdapter(ArrayList<FavouriteDates> favouriteDates) {
        this.favouriteDates = favouriteDates;
    }

    //RecyclerView for "My Dates"
    public CustomAdapter(ArrayList<MyDates> myDates) {
        this.myDates = myDates;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pop_dates_recycler_view, parent, false);
        final CustomViewHolder viewHolder = new CustomViewHolder(view);
        context = parent.getContext();

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        return viewHolder;
    }

}
