package com.quaffon.team9.quaffonbloomington;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by station_1 on 1/30/2015.
 */

public class LogInActivity extends Activity implements View.OnClickListener {
    private static final String Loading_TAG = "Loading Screen Timer";

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    private static final String LOGIN_URL = "http://cgi.soic.indiana.edu/~team9/android/login.php";

    private EditText user, pass;
    //JSON element ids from repsonse of php script:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_loading_screen);

        user = (EditText) findViewById(R.id.emailedit_login);
        pass = (EditText) findViewById(R.id.password_login);

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
        new AttemptLogin().execute();
    }

    //Any type of networking should be done with asynctask.
    class AttemptLogin extends AsyncTask<String, String, String> {

        //three methods get called, first preExecture, then do in background, and once do
        //in back ground is completed, the onPost execture method will be called.

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LogInActivity.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // Check for success tag
            int success;
            String email = findViewById(R.id.emailedit_login).toString();
            String password = findViewById(R.id.password_login).toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("email", email));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                // check your log for json response
                Log.d("Login attempt", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Login Successful!", json.toString());
                    Intent login = new Intent(LogInActivity.this, MainActivity.class);
                    finish();
                    startActivity(login);
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(LogInActivity.this, file_url, Toast.LENGTH_LONG).show();
            }
        }

    }

    public void goToSignUp(View view) {
        Intent goToSignUp = new Intent(this, SignUpActivity.class);
        startActivity(goToSignUp);
    }

    @Override
    public void onClick(View v) {

    }
}
