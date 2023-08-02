package com.myapplicationdev.android.mymovies;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    Button btnMovieList;
    ListView movieList;
    ArrayList<Movie> movies;
    ArrayAdapter<Movie> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTitle().toString());
        stringBuilder.append(" ~ ");
        stringBuilder.append("Show Movie");
        setTitle(stringBuilder.toString());

        btnMovieList = findViewById(R.id.btnMovieList);
        movieList = findViewById(R.id.movieList);
        adapter = new MovieAdapter(this, R.layout.row, movies);
        movieList.setAdapter(adapter);
        btnMovieList.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("movie", movies.get(i));
            }
        });
    }
