package com.example.ridhwaan.redditforfaez.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ridhwaan.redditforfaez.database.RedditDataBase.RedditObjectTable;

/**
 * Created by Ridhwaan on 12/31/16.
 */

public class RedditBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "redditBase.db";
    private static final int VERSION = 1;


    public RedditBaseHelper(Context c){
        super(c,DATABASE_NAME,null,VERSION);

    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("Create Table" + RedditObjectTable.NAME + "(" + "_id integer primary key autoincrement," +
                RedditObjectTable.Cols.AUTHOR + ", " +
                RedditObjectTable.Cols.CONTENT

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




}
