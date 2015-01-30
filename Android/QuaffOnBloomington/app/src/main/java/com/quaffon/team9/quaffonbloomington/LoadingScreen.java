package com.quaffon.team9.quaffonbloomington;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;


public class LoadingScreen extends ActionBarActivity {
    private static final String Loading_TAG = "Loading Screen Timer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loading_screen);

        new CountDownTimer(2000, 1000){
            public void onTick(long millisUntilFinished){
                Log.v(Loading_TAG, "seconds remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                goToLogIn();
                Log.v(Loading_TAG, "done!");
            }
        }.start();
    }

    public void goToLogIn(){
        Intent login_Activity = new Intent(this, LogInScreen.class);
        startActivity(login_Activity);
    }
}
