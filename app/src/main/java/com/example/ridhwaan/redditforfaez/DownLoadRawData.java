package com.example.ridhwaan.redditforfaez;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Ridhwaan on 11/26/16.
 */

public class DownLoadRawData extends AsyncTask<String, Void, String> {
        private String mFileContents;
        public  AsyncResponse delegate = null;




    public String getmFileContents() {
        return mFileContents;
    }

    @Override
    protected String doInBackground(String... strings) {
        mFileContents = downloadRawJSON(strings[0]);

        return mFileContents;
    }






    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        delegate.processFinish(result);

        Log.d("TAG", "RESULT" + result);


    }






    private String downloadRawJSON(String urlSpec){
        Log.d("TAG", "DOWNLOADING" + "  " + urlSpec);
        StringBuilder sb = new StringBuilder();

        try{
            Log.d("TAG", "ENTETRED TRY BLOCK");
            URL url = new URL(urlSpec);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int response = conn.getResponseCode();
            Log.d("TAG" , "RESPONSE CODE" + "  " + response);
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int charRead;
            char[] buffer = new char[500];

            

            while(true){


                charRead = isr.read(buffer); // returns number read + reads into parameter

                if(charRead <= 0){
                    Log.d("TAG","BROKE");
                    break;
                }

                sb.append(String.copyValueOf(buffer,0,charRead));

            }
            return sb.toString();


        }catch (Exception e){
            Log.d("TAG", "FATAL ERROR" + " BECASE   " + e.getMessage());
            e.printStackTrace();
        }

            Log.d("TAG" , " ERROR NO DATA");
            return null;

    }



}



