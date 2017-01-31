package com.example.ridhwaan.redditforfaez;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ridhwaan on 12/30/16.
 */

public class CreateObjectFragment extends DialogFragment {

    public static final String ARG_REDDIT_OBJ = CreateObjectFragment.class.getCanonicalName();

    private EditText mTitleTextView;
    private EditText mContextTextView;
    private RedditObject redditObject;

    private String mAuthor = "Made by User";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.create_object_dialog, null);


        mTitleTextView = (EditText) v.findViewById(R.id.edit_title_dialog_view);
        mContextTextView = (EditText) v.findViewById(R.id.edit_content_dialog_view);


        return new AlertDialog.Builder(getActivity())
                .setTitle("Create Post")
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String titleText = mTitleTextView.getText().toString().trim();
                        String contentText = mContextTextView.getText().toString().trim();

                        if(titleText == " " || titleText == null || TextUtils.isEmpty(titleText)){
                            Toast.makeText(getActivity(), "Empty Title", Toast.LENGTH_SHORT );
                        }

                         redditObject = new RedditObject(

                                titleText,0,mAuthor,contentText


                        );


                        sendData(Activity.RESULT_OK,redditObject);





                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();




    }



    public CreateObjectFragment newInstance( ){


        CreateObjectFragment createObjectFragment = new CreateObjectFragment();
        return createObjectFragment;


    }



    public void sendData( int resultCode, RedditObject obj){

        if(getTargetFragment() == null){

            Log.d("TAG", "TARG FRAG IS NULL");
            return;
        }

        Intent i = new Intent();
        i.putExtra(ARG_REDDIT_OBJ,obj);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);



    }
}
