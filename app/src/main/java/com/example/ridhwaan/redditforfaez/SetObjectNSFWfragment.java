package com.example.ridhwaan.redditforfaez;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;

import android.os.Bundle;

/**
 * Created by Ridhwaan on 12/28/16.
 */

public class SetObjectNSFWfragment extends DialogFragment {


    public static final String ARG_BOOL = "isNSFW";
    private boolean isNSFW;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.set_nsfw_dialog_title)
                .setPositiveButton(android.R.string.ok,

                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                isNSFW = true;

                                sendData(Activity.RESULT_OK, isNSFW);



                            }
                        }


                )
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }


    public static SetObjectNSFWfragment newInstance(boolean isNSFW){
        Bundle args = new Bundle();
        args.putBoolean(ARG_BOOL, isNSFW);
        SetObjectNSFWfragment fragment = new SetObjectNSFWfragment();
        fragment.setArguments(args);
        return fragment;

    }

    private void sendData(int resultCode, boolean isNSFW){

        if(getTargetFragment() == null){
            return;
        }

        Intent i = new Intent();
        i.putExtra(ARG_BOOL,isNSFW);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
        //Pass this to the Target Fragment's onActivityResult method.

    }

}
