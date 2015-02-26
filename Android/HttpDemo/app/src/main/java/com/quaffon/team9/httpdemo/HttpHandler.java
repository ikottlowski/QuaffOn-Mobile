package com.quaffon.team9.httpdemo;

/**
 * Created by station_1 on 2/18/2015.
 */
import org.apache.http.client.methods.HttpUriRequest;

import java.io.UnsupportedEncodingException;

public abstract class HttpHandler {

    public abstract HttpUriRequest getHttpRequestMethod() throws UnsupportedEncodingException;

    public abstract void onResponse(String result);

    public void execute(){
        new AsyncHttpTask(this).execute();
    }
}
