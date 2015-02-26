package com.quaffon.team9.httpdemo;

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Ike on 2/24/2015.
 */
public class OkPost {

    private static final String TAG ="";

    public OkPost() {
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    static String inputJson(String name){//}, String last, String email, String pass){
        return "{\"name\": \"" + name + "\"}";

    }

    OkHttpClient client = new OkHttpClient();

    void post(String url, String json){
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                //String jsonData = response.body().string();
                //Log.v(TAG, jsonData);
                if (response.isSuccessful()){
                    Log.v(TAG, "FAILED" + response.body().toString());
                }
            }
        });
    }
}
