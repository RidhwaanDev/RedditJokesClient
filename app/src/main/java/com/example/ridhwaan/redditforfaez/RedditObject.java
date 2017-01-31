package com.example.ridhwaan.redditforfaez;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Ridhwaan on 11/26/16.
 */

public class RedditObject implements Serializable {
    public static final String URL = "https://www.reddit.com/.json";
   private String mTitle;
   private String mAuthor;
   // private String mDate;
    private int mUpvotes;
  //  private boolean mGilded;
  //  private String mSubReddit;
    private String mContent;
    private UUID postID;
    private boolean isNSFW = false;

    private boolean isClicked = false;


    public UUID getPostID() {
        return postID;
    }

    public RedditObject(String title, int ups, String author, String content){

        postID = UUID.randomUUID();

        this.mAuthor = author;
        this.mTitle = title;
        this.mUpvotes = ups;
        this.mContent = content;

    }

    public boolean isNSFW() {
        return isNSFW;
    }

    public void setNSFW(boolean NSFW) {
        isNSFW = NSFW;
    }

    public void setClicked(boolean isClicked){
        this.isClicked = isClicked;

    }

    public boolean getIsClicked(){
        return isClicked;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

   /* public void setmDate(String mDate) {
        this.mDate = mDate;
    }*/

    public void setmUpvotes(int mUpvotes) {
        this.mUpvotes = mUpvotes;
    }

   /* public void setmGilded(boolean mGilded) {
        this.mGilded = mGilded;
    }*/

  /*  public void setmSubReddit(String mSubReddit) {
        this.mSubReddit = mSubReddit;
    }*/

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }


    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    /*public String getmDate() {
        return mDate;
    }*/

    public int getmUpvotes() {
        return mUpvotes;
    }

  /*  public boolean ismGilded() {
        return mGilded;
    }*/

   /* public String getmSubReddit() {
        return mSubReddit;
    }*/

    public String getmContent() {
        return mContent;
    }

    @Override
    public String toString() {
        return "RedditObject{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mUpvotes=" + mUpvotes +
                ", mContent='" + mContent + '\'' +
                '}';
    }
}
