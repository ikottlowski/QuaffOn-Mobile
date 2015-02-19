package com.quaffon.team9.httpdemo;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button btnRequest, btnPost;
    private EditText etResponse, etPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // httpget button / display text
        btnRequest = (Button) findViewById(R.id.btnRequest);
        etResponse = (EditText) findViewById(R.id.etRespose);

        // httppost button / edit text
        btnPost = (Button) findViewById(R.id.btnPost);
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

    public void onClickPost(View v) {

        new HttpHandler() {
            @Override
            public HttpUriRequest getHttpRequestMethod() {

                // return new HttpGet("http://cgi.soic.indiana.edu/~team9/android/fuckthisshit.html");

                return new HttpPost("http://cgi.soic.indiana.edu/~team9/android/fuckthisshit.html");
            }
            @Override
            public void onResponse(String result) {
                Toast.makeText(getBaseContext(), "Posted!", Toast.LENGTH_LONG).show();
            }

        }.execute();
    }
    // public boolean isConnected(){code is on github...}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
