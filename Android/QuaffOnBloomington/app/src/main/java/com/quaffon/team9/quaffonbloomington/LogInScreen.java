package com.quaffon.team9.quaffonbloomington;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;

/**
 * Created by station_1 on 1/30/2015.
 */

public class LogInScreen extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in_screen);
    }

    public void skipToApp(View view) {
        Intent SkipToApp = new Intent(this, MainActivity.class);
        startActivity(SkipToApp);
    }

    public void goToSignUp(View view) {
        Intent goToSignUp = new Intent(this, SignUpActivity.class);
        startActivity(goToSignUp);
    }
}
