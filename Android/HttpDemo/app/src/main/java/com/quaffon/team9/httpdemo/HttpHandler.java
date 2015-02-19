package com.quaffon.team9.httpdemo;

/**
 * Created by station_1 on 2/18/2015.
 */
import org.apache.http.client.methods.HttpUriRequest;

public abstract class HttpHandler {

    public abstract HttpUriRequest getHttpRequestMethod();

    public abstract void onResponse(String result);

    public void execute(){
        new AsyncHttpTask(this).execute();
    }
}
