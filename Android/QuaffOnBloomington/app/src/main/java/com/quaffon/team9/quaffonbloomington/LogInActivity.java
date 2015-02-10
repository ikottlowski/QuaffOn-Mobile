package com.quaffon.team9.quaffonbloomington;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

/**
 * Created by station_1 on 1/30/2015.
 */

public class LogInActivity extends Activity {
    private static final String Loading_TAG = "Loading Screen Timer";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_loading_screen);

        new CountDownTimer(2000, 1000){
            public void onTick(long millisUntilFinished){
                Log.v(Loading_TAG, "seconds remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                setContentView(R.layout.activity_log_in_screen);
                Log.v(Loading_TAG, "done!");
            }
        }.start();

    }

    public void skipToApp(View view) {
        Intent SkipToApp = new Intent(this, MainActivity.class);
        startActivity(SkipToApp);
    }

    public void login(View view){
        //some php here to check if the values that are passed are correct
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }

    public void goToSignUp(View view) {
        Intent goToSignUp = new Intent(this, SignUpActivity.class);
        startActivity(goToSignUp);
    }
}
