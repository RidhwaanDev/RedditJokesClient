package com.example.ridhwaan.redditforfaez;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDoneException;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class DetailActivity extends SingleFragmentActivity {
        public static final String ID = DetailActivity.class.getCanonicalName();

    @Override
    public Fragment createFragment() {

        UUID redditID = (UUID) getIntent().getSerializableExtra(ID);
      //  return new DetailActivityFragment().newInstance(redditID);
        return null;

    }

    public static Intent startIntent(Context packageContext, UUID id){
        Intent i = new Intent(packageContext,DetailActivity.class);
        i.putExtra( ID , id);
        return  i;



    }
}

