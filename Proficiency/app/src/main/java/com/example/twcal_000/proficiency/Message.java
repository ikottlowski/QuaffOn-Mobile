package com.example.twcal_000.proficiency;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Ike on 1/26/2015.
 */
public class Message {
    public static void message(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
