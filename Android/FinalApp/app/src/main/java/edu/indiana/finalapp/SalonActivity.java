package edu.indiana.finalapp;

/*SalonActivity.java
*
*Created by: Kelly McGuinn
*Last Modified by: Kelly McGuinn
*Last Modified on: 3/6/15
*Assignment/Project: A290 Android Developement
*Part of: Final App
**/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;



public class SalonActivity extends Activity implements OnClickListener {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salon_activity);
		
		View cream = findViewById(R.id.cream);
        cream.setOnClickListener(this);
        
        View alcohol = findViewById(R.id.alcohol);
        alcohol.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.cream) {
			Intent i = new Intent(this, SalonWrong.class);
			startActivity(i);
		} else if (id == R.id.alcohol) {
			Intent i = new Intent(this, Salon2Activity.class);
			startActivity(i);
		}}
}
		
