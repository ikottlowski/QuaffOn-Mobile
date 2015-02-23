package com.example.ike.okhttpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private PageData pageData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String testURL = "http://cgi.soic.indiana.edu/~team9/JSON/index.php";
        OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(testURL)
                    .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) { }

            @Override
            public void onResponse(Response response) throws IOException {
                String jsonData = response.body().string();
                Log.v(TAG, jsonData);
                if (response.isSuccessful()){
                    try {
                        pageData = getCurrentDetails(jsonData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.v(TAG, response.body().toString());
                }
            }
        });
    }

    private PageData getCurrentDetails(String jsonData) throws JSONException{
        JSONObject data = new JSONObject(jsonData);
        String name = data.getString("name");
        Log.i(TAG, "From JSON: " + name);

        return null;
    }
}
