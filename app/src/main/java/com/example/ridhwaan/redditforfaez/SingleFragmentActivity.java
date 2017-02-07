package com.example.ridhwaan.redditforfaez;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ridhwaan on 11/26/16.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {


    public abstract Fragment createFragment();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout_generic);


        FragmentManager fm = getSupportFragmentManager();
        // First we need fragment manager to build any fragment
        //Next we look for any fragment that has the given ID. If it
        //does then dont do anything
        //If its empty then lets create a fragment

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){

            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }


    }
}
