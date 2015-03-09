package com.jessiepusateri.a290final;

/*HistoryActivity.java
Contains the Java code for the History Activity in this project.
Created by: Jessie Pusateri
Created on: 3/6/15
Last Modified by: Jessie Pusateri
Last Modified on: 3/6/15
Assignment/Project: A290 Android Development Final
Part of: A290Final*/

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HistoryActivity extends Activity{
	//initializing variables
	public static final int DEFAULT = -1;
	TableLayout HistoryLayout;
	TableRow Row1;
	TableRow Row2;
	TableRow Row3;
	TableRow Row4;
	TableRow Row5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		
		
		//creates the objects so we can reference them later
		HistoryLayout = (TableLayout) findViewById(R.id.HistoryTableLayout);
		Row1 = (TableRow) findViewById(R.id.tableRow1);
		Row2 = (TableRow) findViewById(R.id.tableRow2);
		Row3 = (TableRow) findViewById(R.id.tableRow3);
		Row4 = (TableRow) findViewById(R.id.tableRow4);
		Row5 = (TableRow) findViewById(R.id.tableRow5);

		
		loadPrefs(1, Row1); //Row 1
		loadPrefs(2, Row2); //Row 2
		loadPrefs(3, Row3); //Row 3
		loadPrefs(4, Row4); //Row 4
		loadPrefs(5, Row5); //Row 5
	}

	
	//goes through all of shared preferences and shows them
	public void loadPrefs(int itercounter, TableRow myrow)
	{
		SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
		
		int guess = sharedPrefs.getInt("guess" + String.valueOf(itercounter), DEFAULT);
		int actual = sharedPrefs.getInt("actual" + String.valueOf(itercounter), DEFAULT);
		
		String guess_as_string;
		String actual_as_string;
		String difference_as_string;
		
		//if there isn't an entry, display nothing
		if (guess == DEFAULT || actual == DEFAULT)
		{
			guess_as_string = " ";
			actual_as_string = " ";
			difference_as_string = " ";
		}
		
		//else, display the entry
		else
		{
			guess_as_string = String.valueOf(guess);
			actual_as_string = String.valueOf(actual);
			difference_as_string = String.valueOf(guess - actual);
		}
		
		//create guess textview
		TextView tvguess = new TextView(this);
		tvguess.setText(guess_as_string);
		tvguess.setGravity(Gravity.CENTER);
		tvguess.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
		
		//create actual textview
		TextView tvactual = new TextView(this);
		tvactual.setText(actual_as_string);
		tvactual.setGravity(Gravity.CENTER);
		tvactual.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
		
		//create difference textview
		TextView tvdifference = new TextView(this);
		tvdifference.setText(difference_as_string);
		tvdifference.setGravity(Gravity.CENTER);
		tvdifference.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);

		
		//add to corresponding rows
		if (itercounter == 1)
		{
			this.Row1.addView(tvguess);
			this.Row1.addView(tvactual);
			this.Row1.addView(tvdifference);
		}
		
		if (itercounter == 2)
		{
			this.Row2.addView(tvguess);
			this.Row2.addView(tvactual);
			this.Row2.addView(tvdifference);
		}
		
		if (itercounter == 3)
		{
			this.Row3.addView(tvguess);
			this.Row3.addView(tvactual);
			this.Row3.addView(tvdifference);
		}
		
		if (itercounter == 4)
		{
			this.Row4.addView(tvguess);
			this.Row4.addView(tvactual);
			this.Row4.addView(tvdifference);
		}
		
		if (itercounter == 5)
		{
			this.Row5.addView(tvguess);
			this.Row5.addView(tvactual);
			this.Row5.addView(tvdifference);
		}
		
	}
}
