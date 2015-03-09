package edu.indiana.finalapp;

/*Salon2Activity.java
*
*Created by: Kelly McGuinn
*Last Modified by: Kelly McGuinn
*Last Modified on: 3/6/15
*Assignment/Project: A290 Android Developement
*Part of: Final App
**/

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;


public class Salon2Activity extends Activity implements OnClickListener {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salon2activity);
		
		View cream2 = findViewById(R.id.cream2);
        cream2.setOnClickListener(this);
        
        View alcohol2 = findViewById(R.id.alcohol2);
        alcohol2.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.alcohol2) {
			Intent i = new Intent(this, SalonWrong.class);
			startActivity(i);
		} else if (id == R.id.cream2) {
			Intent i = new Intent(this, SalonFinish.class);
			startActivity(i);
		}
	}
}