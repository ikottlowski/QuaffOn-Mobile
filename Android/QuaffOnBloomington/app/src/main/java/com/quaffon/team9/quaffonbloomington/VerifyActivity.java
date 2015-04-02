package com.quaffon.team9.quaffonbloomington;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;


public class VerifyActivity extends Activity {

    private static final String Loading_TAG = "Loading Screen Timer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_loading_screen);

        final Intent intent = new Intent(this, MainActivity.class);
        new CountDownTimer(2000, 1000){
            public void onTick(long millisUntilFinished){
                Log.v(Loading_TAG, "seconds remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                setContentView(R.layout.activity_verify);
                findViewById(R.id.textView3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intent);
                    }
                });
                Log.v(Loading_TAG, "done!");
            }
        }.start();
    }
}