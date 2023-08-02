package com.myapplicationdev.android.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShow;
    EditText movieTitle, etGenre, etYear;
    Spinner spnRating;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnMovieList);
        movieTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spnRating = findViewById(R.id.spnRating);

        this.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = movieTitle.getText().toString().trim();
                String genre = etGenre.getText().toString().trim();
                if(title.length()!=0 && genre.length()!=0){
                    try{
                        int year =  Integer.valueOf(etYear.getText().toString().trim());
                        dbHelper = new DBHelper(MainActivity.this);
                        dbHelper.insertMovie(title, genre, year, getRating());
                        dbHelper.close();
                        Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                        movieTitle.setText("");
                        etGenre.setText("");
                        etYear.setText("");
                        return;
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });

        this.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
    }
}