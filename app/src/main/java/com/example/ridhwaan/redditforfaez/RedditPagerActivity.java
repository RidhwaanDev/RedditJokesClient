package com.example.ridhwaan.redditforfaez;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;
import java.util.UUID;

/**
 * Created by Ridhwaan on 12/26/16.
 */

public class RedditPagerActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private List<RedditObject> mRedditObjects;
    public static final String INTENT_ID = RedditPagerActivity.class.getCanonicalName();



    public static Intent newInstance(Context packageContext, UUID ID){
        Intent intent = new Intent(packageContext, RedditPagerActivity.class);
        intent.putExtra(INTENT_ID, ID);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reddit_crime_pager_layout);





         UUID redditID = (UUID) getIntent().getSerializableExtra(INTENT_ID);



        viewPager = (ViewPager) findViewById(R.id.view_pager);
        mRedditObjects = RedditObjectStash.get(this).redditObjectsList;
        FragmentManager fragmentManager = getSupportFragmentManager();







        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager)
        {
            @Override
            public Fragment getItem(int position) {
                RedditObject redditObject = mRedditObjects.get(position);
                Log.d("VIEW PAGER ID", " GET ID" + redditObject.getPostID());
                //return  DetailActivityFragment.newInstance(redditObject.getPostID());
                return DetailActivityFragment.newInstance(redditObject.getPostID());
            }

            @Override
            public int getCount() {
                return mRedditObjects.size();
            }
        });

        for(int i = 0; i< mRedditObjects.size(); i++){
            if(mRedditObjects.get(i).getPostID().equals(redditID)){
                viewPager.setCurrentItem(i);
                break;
            }
        }





    }
}
