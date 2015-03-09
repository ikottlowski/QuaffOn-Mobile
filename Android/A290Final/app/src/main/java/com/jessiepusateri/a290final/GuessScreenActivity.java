package com.jessiepusateri.a290final;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/*GuessScreenActivity.java
Contains the Java code for the Guess Screen Activity in this project.
Created by: Jessie Pusateri
Created on: 3/6/15
Last Modified by: Jessie Pusateri
Last Modified on: 3/6/15
Assignment/Project: A290 Android Development Final
Part of: A290Final*/
public class GuessScreenActivity extends Activity{
	EditText UserGuess;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess_screen);
		
		//creates a textview so we can see its values later
		UserGuess = (EditText) findViewById(R.id.guessEditText);
		
	}
	
	
	//sets the guess, saves it and moves on the the results activity
	public void setGuess(View v)
	{
		
		//get the user indicated guess
		int myGuess = Integer.parseInt(UserGuess.getText().toString());
		
		//put data in sharedPrefs
		SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPrefs.edit();
		
		int counter = sharedPrefs.getInt("counter", -1);
		editor.putInt("guess" + String.valueOf(counter), myGuess);
		editor.commit();

		//goes to the guess results activity
		Intent r = new Intent(this, GuessResultsActivity.class);
    	startActivity(r);
		
	}
	
	
	

}
