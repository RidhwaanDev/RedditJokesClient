package com.example.ridhwaan.redditforfaez;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ridhwaan on 11/29/16.
 */

public class Parser implements AsyncResponse  {

    private String mTextOfURL;
    private  List<RedditObject> redditObjectsList;
    JSONObject resultObject;
    JSONObject getDataObject;
    JSONArray childrenArrayObject;

    public Parser(String text) {
            Log.d("PARSER", " TEXT WAS" +  text);
          // this.mTextOfURL = text;

        redditObjectsList = new ArrayList<>();

        try {
            //Parser parser = new Parser(mFileContents);
            resultObject = new JSONObject(text);
            getDataObject = resultObject.getJSONObject("data");
            childrenArrayObject = getDataObject.getJSONArray("children");
            for(int i = 0; i < childrenArrayObject.length(); i++){

                JSONObject getData = childrenArrayObject.getJSONObject(i).getJSONObject("data");



                String author = getData.getString("author");
                String title = getData.getString("title");
                int ups = getData.getInt("ups");
                String content = getData.getString("selftext");



                Log.d("TAG", "Author is  "  + author + " Title  " + title + " Upvotes" + " " + ups);


                //RedditObject redditObject = new RedditObject(title, ups , author);


                redditObjectsList.add(new RedditObject(title , ups , author,content));
                 }
         //   Log.d("TAG", "RESULT OF LIST" + redditObjectsList.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


   }


    public List<RedditObject> getRedditObjectsList() {
        return redditObjectsList;
    }

    @Override
    public void processFinish(String output) {
        Log.d("TAG" , "process finish receied");
        mTextOfURL = output;
    }
}