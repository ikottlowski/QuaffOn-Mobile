/*URActivity.java
*
* Broadcast for the reminders
*
*
*Created by: Ryder McMinn
*Created on: 3/1/15
*Last Modified by: Ryder McMinn
*Last Modified on: 3/6/15
*Assignment/Project: A290 Final project
*Part of: Uber Reminder
**/

package com.rydermcminn.uberreminder;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

public class URBroadcastReceiver extends BroadcastReceiver {

	final public static String ONE_TIME = "onetime";
	private String infoTag = "INFO";

	@Override
	public void onReceive(Context context, Intent intent) {
		/* PowerManager.WakeLock
		 * Keeps system awake
		 */
		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(
				PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
		
		// Acquire the lock to keep persistent reminder ready
		wl.acquire();
		
		//Receive additional params from intent
		Bundle extras = intent.getExtras();
		StringBuilder msgStr = new StringBuilder();
		
		//Should only show if it's a one-time reminder
		if (extras != null && extras.getBoolean(ONE_TIME)) {
			msgStr.append("One time Timer : ");
		}
		Format formatter = new SimpleDateFormat("hh:mm:ss a");
		msgStr.append(formatter.format(new Date()));

		Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();

		// Release the lock
		wl.release();

	}

	public void SetAlarm(Context context) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, URBroadcastReceiver.class);
		intent.putExtra(ONE_TIME, Boolean.FALSE);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		// After after 30 seconds
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
				1000 * 5, pi);
		
		//Log
		Log.d(infoTag, "Repeating Alarm Set");
	}

	public void CancelAlarm(Context context) {
		Intent intent = new Intent(context, URBroadcastReceiver.class);
		PendingIntent sender = PendingIntent
				.getBroadcast(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
		
		//Alarm Canceled Log / Toast
		Toast.makeText(context, "Repeating Alarm Canceled", Toast.LENGTH_LONG).show();
		Log.d(infoTag, "Repeating Alarm Canceled");
	}

	public void setOnetimeTimer(Context context) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, URBroadcastReceiver.class);
		intent.putExtra(ONE_TIME, Boolean.TRUE);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
		
		//Log
		Log.d(infoTag, "One Time Alarm Set");
	}
}
