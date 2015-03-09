package com.jessiepusateri.a290final;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/*GuessResultsActivity.java
Contains the Java code for the Guess Results Activity in this project.
Created by: Jessie Pusateri
Created on: 3/6/15
Last Modified by: Jessie Pusateri
Last Modified on: 3/6/15
Assignment/Project: A290 Android Development Final
Part of: A290Final*/

public class GuessResultsActivity extends Activity implements OnClickListener {
	public static final int DEFAULT = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess_results);
		
		View MainMenuButton = findViewById(R.id.MainMenuButton);
        MainMenuButton.setOnClickListener(this);
        View HistoryButton = findViewById(R.id.HistoryButton);
        HistoryButton.setOnClickListener(this);
		
		
		SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
		
		int counter = sharedPrefs.getInt("counter",DEFAULT);
		int guess = sharedPrefs.getInt("guess" + String.valueOf(counter), DEFAULT);
		int actual = sharedPrefs.getInt("actual" + String.valueOf(counter), DEFAULT);
		
		Log.d("GuessResultsActivity", String.valueOf(counter));
		
		if(guess == DEFAULT || actual == DEFAULT)
		{
			Toast.makeText(this,"No Data was Found", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(this,"Data Loaded Successfully", Toast.LENGTH_LONG).show();
		}
	
	//finds textview and changes it to the user's guess
	TextView g = new TextView(this);
	g = (TextView) findViewById(R.id.Guess);
	g.setText(String.valueOf(guess) + " seconds");
	
	//finds textview and changes it to the user's guess
	TextView a = new TextView(this);
	a = (TextView) findViewById(R.id.ActualTime);
	a.setText(String.valueOf(actual) + " seconds");
	
	TextView d = new TextView(this);
	d = (TextView) findViewById(R.id.Difference);
	d.setText(String.valueOf(guess - actual) + " seconds");
	}
	
	public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.MainMenuButton:
    	Intent h = new Intent(this, MainActivity.class);
    	startActivity(h);
    	break;
    	case R.id.HistoryButton:
        Intent i = new Intent(this, HistoryActivity.class);
        startActivity(i);
        break;
    	}
    }
}
