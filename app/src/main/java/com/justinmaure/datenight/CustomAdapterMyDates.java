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
import android.widget.TextView;

import com.justinmaure.datenight.Objects.Date;

import java.util.ArrayList;

/**
 * Created by Max on 3/31/18.
 */

public class CustomAdapterMyDates extends RecyclerView.Adapter {
    private ArrayList<Date> dates;


    Context context;

    //RecyclerView for "My Dates"
    public CustomAdapterMyDates(ArrayList<Date> dates) {
        this.dates = dates;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_dates_recycler_view, parent, false);
        final CustomViewHolder viewHolder = new CustomViewHolder(view);
        context = parent.getContext();

        /**
         * Needs revising. Make sure to fully understand how this works before moving on
         * Need to make the long click delete a date, with the delete confirmation
         * regular click will allow the user to edit the date
         * there will not be any favoriting since it's the user's own date ideas.
         * Figure out what the rating thing will be for the user's dates
         */

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete Date")
                        .setMessage("Are you sure you want to delete this date?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int location = viewHolder.getAdapterPosition();
                                DatabaseHelper db = new DatabaseHelper(context);
                                db.deleteDate(dates.get(location).getId());
                                dates.remove(location);
                                notifyItemRemoved(location);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return false;
            }
        });

        /**
         * Also needs revising.
         * This will simply allow the user to edit their date idea.
         * Most of this is already set up but the update date fragment needs to be
         * created first before this will gain any functionality
         *
         */
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) context;
                int location = viewHolder.getAdapterPosition();
                FragmentManager fm = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.content,
                        UpdateDateFragment.newInstance(dates.get(location)));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Date date = dates.get(position);
        ((CustomViewHolder) holder).dateName.setText(date.getDateName());
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
        protected ImageView picture;
        protected ImageView isPublic;

        public CustomViewHolder(View view){
            super(view);
            this.dateName = (TextView) view.findViewById(R.id.dateName);
            this.description = (TextView) view.findViewById(R.id.description);
            this.picture = (ImageView) view.findViewById(R.id.picture);
            this.isPublic = (ImageView) view.findViewById(R.id.isPublic);

        }
    }

}
