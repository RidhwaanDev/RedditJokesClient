package com.example.ridhwaan.redditforfaez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.ridhwaan.redditforfaez.database.RedditBaseHelper;
import com.example.ridhwaan.redditforfaez.database.RedditDataBase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ridhwaan on 12/3/16.
 *
 * This class ia singleton. I use a singleton to ensure that only one isntance of RedditObjectStash exists throughout the apps
 * lifetime
 *
 *
 * I lik bg buts nd I carrot li
 */

public class RedditObjectStash  {
    Parser parse;

    private static RedditObjectStash sRedditObjectStash;

    public List<RedditObject> redditObjectsList;

    private Context c;
    private SQLiteDatabase redditDB;


    public static RedditObjectStash get(Context context){



        if(sRedditObjectStash == null){
            sRedditObjectStash = new RedditObjectStash(context);


        }

        return sRedditObjectStash;


    }

    private RedditObjectStash (Context context){

        c = context.getApplicationContext();

        redditDB = new RedditBaseHelper(c).getWritableDatabase();






        Log.d("TAG", "Constructor called");

    }

    public void addRedditObject(RedditObject redditObject){

        redditObjectsList.add(redditObject);
    }

    public RedditObject getRedditObject(UUID id){

        for(RedditObject redditObject : redditObjectsList  ){
            if(redditObject.getPostID().equals(id)){
                return redditObject;
            }


        }
        return null;
    }


    public  void setList(List<RedditObject> list){

       redditObjectsList = list;
    }











}
