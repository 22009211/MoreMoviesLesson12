package com.myapplicationdev.android.mymovies;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class MovieAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayAdapter<Movie> movieList;

    public MovieAdapter(Context context, int resource, ArrayAdapter<Movie> objects) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.movieList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(resource, parent,false);

            TextView tvTitle = rowView.findViewById(R.id.tvTitle);
            TextView tvGenre = rowView.findViewById(R.id.tvGenre);
            TextView tvYear= rowView.findViewById(R.id.tvYear);
            Spinner rating = rowView.findViewById(R.id.spnRating);

            Contact currentItem = contactList.get(position);
            tvTitle.setText(currentItem.getTitle());
            tvGenre.setText("+" + currentItem.getGenre());
            tvYear.setText(currentItem.getYear());
            rating.set

            return rowView;
        }
    }