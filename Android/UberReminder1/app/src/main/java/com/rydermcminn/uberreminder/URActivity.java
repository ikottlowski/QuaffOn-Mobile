/*URActivity.java
*
* Main Menu / Main Activity for Uber Reminder
*
*
*Created by: Ryder McMinn
*Created on: 3/1/15
*Last Modified by: Ryder McMinn
*Last Modified on: 3/6/15
*Assignment/Project: A290 Final project
*Part of: Uber Reminder, refers to activity_main.xml layout file and URBroadcastReceiver.java
**/

package com.rydermcminn.uberreminder;

import com.rydermcminn.uberreminder.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.util.Log;

public class URActivity extends Activity {

	private URBroadcastReceiver alarm;
	private String infoTag = "INFO";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(infoTag, "onCreate() Called");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		alarm = new URBroadcastReceiver();
	}

	@Override
	protected void onStart() {
		Log.d(infoTag, "onStart() Called");
		super.onStart();
	}
	
	//repeats toast every 30 seconds
	public void startRepeatingTimer(View view) {
		Log.d(infoTag, "startRepeatingTimer() Called");
		Context context = this.getApplicationContext();
		if (alarm != null) {
			alarm.SetAlarm(context);
		} else {
			Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
		}
	}

	public void cancelRepeatingTimer(View view) {
		Log.d(infoTag, "cancelRepeatingTimer() Called");
		Context context = this.getApplicationContext();
		if (alarm != null) {
			alarm.CancelAlarm(context);
		} else {
			Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
		}
	}
	
	//repeats toast once
	public void onetimeTimer(View view) {
		Log.d(infoTag, "onetimeTimer() Called");
		Context context = this.getApplicationContext();
		if (alarm != null) {
			alarm.setOnetimeTimer(context);
		} else {
			Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d(infoTag, "onCreateOptionsMenu() Called");
		getMenuInflater().inflate(R.menu.activity_widget, menu);
		return true;
	}

}
