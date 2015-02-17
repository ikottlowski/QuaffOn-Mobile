//* Contains the primary code for the
//Java Files

// Created by: Alyeesha Puri
// * Created on: 01/29/2015
//Last modified by: Alyeesha Puri
//* Last modified on: 02/013/2015
 //* Assignment / Project: Assignment 2 - My First Activity 
//



package edu.indiana.myfirstactivity;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.util.Log;
import android.view.Window;

public class FirstActivity extends Activity {
	
	String tag = "Lifecycle";
	/* Called when activity is first created */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivity_main);
        
        /* hides the title bar */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.firstactivity_main);
        Log.d(tag, "In the onCreate() event");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {      
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void onStart()  {
    	super.onStart();
    	Log.d(tag, "In the onStart() event");
    }
    
    public void onRestart()  {
    	super.onRestart();
    	Log.d(tag, "In the onRestart() event");
    }
    
    public void onResume()  {
    	super.onResume();
    	Log.d(tag, "In the onResume() event");
    }
    
    public void onPause()  {
    	super.onPause();
    	Log.d(tag, "In the onPause() event");
    }
    
    public void onStop()  {
    	super.onStop();
    	Log.d(tag, "In the onStop() event");
    }
    
    public void onDestroy()  {
    	super.onDestroy();
    	Log.d(tag, "In the onDestroy() event");
    }

}
