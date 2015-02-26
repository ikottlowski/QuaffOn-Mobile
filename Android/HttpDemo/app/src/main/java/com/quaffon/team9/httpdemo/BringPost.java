package com.quaffon.team9.httpdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ike on 2/25/2015.
 */
public class BringPost extends AsyncTask<String, String, String> {
        JSONParser jsonParser = new JSONParser();
        String LOGIN_URL = "http://cgi.soic.indiana.edu/~team9/Database/index.php";

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";

    /**
         * Before starting background thread Show Progress Dialog
         * */
        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();/*
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show(); */
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
 /*           String username = user.getText().toString();
            String password = pass.getText().toString();
 */
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", "Brains"));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                // full json response
                Log.d("Login attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("User Created!", json.toString());
                    //finish();
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
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            //pDialog.dismiss();
            //if (file_url != null){
                //Toast.makeText(BringPost.this, file_url, Toast.LENGTH_LONG).show();
            //}

        }

    }
