package com.example.ridhwaan.redditforfaez;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Ridhwaan on 11/26/16.
 */

public class DetailActivityFragment extends Fragment //implements AsyncResponse  // {
{

    private TextView mTitleOfPost, mContentOfPost, mUpvote;
    private boolean mIsNSFW = false;
    private String resultOfTask;
    private RedditObject redditObject;
    private Switch mTagSwitch;
    private static final String DIALOG_ID = "NSFW_DIALOG";
    private static final int REQUEST_TO_FRAGMENT_ID = 0;


    private static final String ARGS_ID ="reddit_id";






    public static DetailActivityFragment newInstance(UUID id ){
        Bundle args = new Bundle();
        args.putSerializable(ARGS_ID,id);

        DetailActivityFragment detailActivityFragment = new DetailActivityFragment();
        detailActivityFragment.setArguments(args);

        return detailActivityFragment;


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_fragment_layout,container,false);




        mTitleOfPost = (TextView) v.findViewById(R.id.title_reddit_post);
        mContentOfPost = (TextView) v.findViewById(R.id.content_reddit);
        mUpvote = (TextView) v.findViewById(R.id.upvote_reddit_post);
        mTagSwitch = (Switch) v.findViewById(R.id.set_nsfw_switch);
        mTagSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!mTagSwitch.isChecked()){

                    createDialogFragment();

                }
 }
        });

        //Parser parser = new Parser(resultOfTask);
      //  Log.d("TAG", "INTERFACE RESULT IS  "  + resultOfTask);
        //UUID redditID = (UUID) getActivity().getIntent().getSerializableExtra(RedditPagerActivity.INTENT_ID);
        UUID redditID = (UUID)getArguments().getSerializable(ARGS_ID);

        if(redditID == null){
            throw new NullPointerException();
        } //else{Log.d("UUID" , " ID WAS " +  redditID.toString());





       redditObject = RedditObjectStash.get(getActivity()).getRedditObject(redditID);
       // Log.d("varISSUE", "redditObject ID" + redditObject.getPostID());
        Log.d("UUID", " ID WAS" + redditID);
        mTitleOfPost.setText(redditObject.getmTitle() + "   " + "By:" + redditObject.getmAuthor());
        mContentOfPost.setText(redditObject.getmContent());
        mUpvote.setText(Integer.toString(redditObject.getmUpvotes()));



       redditObject.setClicked(true);

        v.invalidate();

        return v;
    }

    public void createDialogFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        SetObjectNSFWfragment dialogFragment = SetObjectNSFWfragment.newInstance(mIsNSFW);
        dialogFragment.show(fragmentManager,DIALOG_ID);
        dialogFragment.setTargetFragment(DetailActivityFragment.this, REQUEST_TO_FRAGMENT_ID);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK){return;}


        if(requestCode == REQUEST_TO_FRAGMENT_ID){

                    if (data.getBooleanExtra(SetObjectNSFWfragment.ARG_BOOL,false)) {

                        mTitleOfPost.setTextColor(Color.RED);


                    }


        }

    }
}
