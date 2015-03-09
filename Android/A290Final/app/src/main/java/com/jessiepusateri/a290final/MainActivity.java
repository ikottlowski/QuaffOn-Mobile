package com.jessiepusateri.a290final;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

/*MainActivity.java
Contains the Java code for the Main Activity in this project.
Created by: Jessie Pusateri
Created on: 3/6/15
Last Modified by: Jessie Pusateri
Last Modified on: 3/6/15
Assignment/Project: A290 Android Development Final
Part of: A290Final*/
//icon designed by Freepik.com


//shows the main menu and lets you click buttons to do more activities
public class MainActivity extends Activity implements OnClickListener {
	NotificationManager NM;
	Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View HistoryButton = findViewById(R.id.HistoryButton);
        HistoryButton.setOnClickListener(this);
        View InstructionsButton = findViewById(R.id.InstructionsButton);
        InstructionsButton.setOnClickListener(this);
        //View NotificationButton = findViewById(R.id.NotificationButton);
        //NotificationButton.setOnClickListener(this);
        
        //Toast.makeText(getBaseContext(), "RUNNER1", Toast.LENGTH_LONG).show();
        
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    
    //sets the click functionality for the buttons
    public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.HistoryButton:
    	Intent h = new Intent(this, HistoryActivity.class);
    	startActivity(h);
    	break;
    	case R.id.InstructionsButton:
        Intent i = new Intent(this, InstructionsActivity.class);
        startActivity(i);
        break;
    	}
    }
    
    //create a notification
    @SuppressLint("NewApi") public void createNotification() {
        // Prepare intent which is triggered if the notification is selected
        Intent intent = new Intent(this, GuessScreenActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Build notification
        Notification noti = new Notification.Builder(this)
            .setContentTitle("Times up!")
            .setContentText("Click here to guess elapsed time.").setSmallIcon(R.drawable.ic_launcher)
            .setContentIntent(pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
      }
    
    //sets the random notification time and stores it in Shared Preferences
    public void setNotificationTime(View view)
    {
    	
    	//access shared preferences and make changes
    	SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPrefs.edit();
		//editor.putInt("counter", -1);
		//editor.commit();
		int counter = sharedPrefs.getInt("counter", -1);
		editor.putInt("counter", counter + 1);
		
		//makes the time random
    	Random rand = new Random();
    	int actualtime = rand.nextInt(60) + 1;
    	
    	//let's user know that the timer has started
    	Toast.makeText(getBaseContext(), "The timer has started!", Toast.LENGTH_LONG).show();
    	
    	//sets the event that happens later
    	handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
            	Toast.makeText(getBaseContext(), "Time's up! Click the notification to guess!", Toast.LENGTH_LONG).show();
            	createNotification();
            }
        };
        
        //sets when timer actually goes off
        handler.postDelayed(r, 1000 * actualtime);
        
        
		editor.putInt("actual" + String.valueOf(counter + 1), actualtime);
		editor.commit();
		Log.d("setNotificationTime", String.valueOf(counter));
    }
   
    

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
