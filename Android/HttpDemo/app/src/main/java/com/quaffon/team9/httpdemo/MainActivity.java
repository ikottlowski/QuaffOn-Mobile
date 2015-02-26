package com.quaffon.team9.httpdemo;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.text.LoginFilter;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private EditText etResponse, etPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // httpget button / display text
        etResponse = (EditText) findViewById(R.id.etRespose);

        // httppost button / edit text
        etPost = (EditText) findViewById(R.id.etPost);

    }

    public void onClickGet(View v) {

        new HttpHandler() {
            @Override
            public HttpUriRequest getHttpRequestMethod() {

                return new HttpGet("http://cgi.soic.indiana.edu/~team9/Test/ike.php");

                // return new HttpPost(url)
            }
            @Override
            public void onResponse(String result) {
                Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
                etResponse.setText(result);
            }

        }.execute();
    }

    public void onClickPost(View v) throws IOException {
/*
        OkPost post = new OkPost();
        String json = OkPost.inputJson("ike");
        post.post("http://cgi.soic.indiana.edu/~team9/Database/index.php", json);
*/
        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost("http://cgi.soic.indiana.edu/~team9/Database/index.php");
        List<NameValuePair> nameValuePairs = new ArrayList<>(1);
        nameValuePairs.add(new BasicNameValuePair("name", "zombies"));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = httpClient.execute(httpPost);

        new HttpHandler() {
            @Override
            public HttpUriRequest getHttpRequestMethod() throws UnsupportedEncodingException {
                HttpPost httpPost = new HttpPost("http://cgi.soic.indiana.edu/~team9/Database/index.php");

                List<NameValuePair> nameValuePairs = new ArrayList<>(1);
                nameValuePairs.add(new BasicNameValuePair("name", "zombies"));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                return httpPost;

            }
            @Override
            public void onResponse(String result) {
                Toast.makeText(getBaseContext(), "Posted!", Toast.LENGTH_LONG).show();
            }

        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
