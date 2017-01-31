package com.example.ridhwaan.redditforfaez;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RedditListActivity extends SingleFragmentActivity {



    @Override
    public Fragment createFragment() {
        return new RedditListFragment();
    }
}
