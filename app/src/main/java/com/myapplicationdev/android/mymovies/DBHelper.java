package com.myapplicationdev.android.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "movies.db";
    private static final String TABLE_MOVIE = "Movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE Movie(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, genre TEXT, year INTEGER, rating TEXT)";
        db.execSQL(createTableSql);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createTableSql);
        stringBuilder.append("\ncreated tables");
        Log.i("info", stringBuilder.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }

    public void insertMovie(String title, String genre, int year, String rating) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);
        long result = db.insert(TABLE_MOVIE, null, values);
        db.close();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(result);
        Log.d("SQL Insert", stringBuilder.toString());

        return result;
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id,title,genre,year,rating FROM Movie", null);
        if (cursor.moveToFirst()){
            do{
                movieList.add(new Movies(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movieList;
    }

    public ArrayList<Movie> getAllSongsByStars(int ratingFilter) {
        ArrayList<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase cursor = db;
        Cursor cursor2 = cursor.query(TABLE_MOVIE, new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING}, "rating >= 7", new String[]{String.valueOf(ratingFilter)}, null, null, null, null);
        if (cursor2.moveToFirst()) {
            do {
                movieList.add(new Movies(cursor2.getInt(0),cursor2.getString(1),cursor2.getString(2), cursor2.getInt(3),cursor2.getString(4)));
            } while (cursor2.moveToNext());
        }
        cursor.close();
        db.close();
        return movieList;
    }

    public int updateMovie(Movie data){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_GENRE, data.getGenre());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_RATING, data.getRating());
        String[] args = new String[]{String.valueOf(data.getId())};
        int result =  db.update(TABLE_MOVIE, values, "id = ?", args);
        db.close();
        return result;
    }

    public int deleteMovie(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = new String[]{String.valueOf(id)};
        int result = db.delete(TABLE_MOVIE, "id = ?", args);
        db.close();
        return result;
    }
}
