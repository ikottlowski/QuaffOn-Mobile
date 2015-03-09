package edu.indiana.finalapp;

/*SalonFinish.java
*
*Created by: Kelly McGuinn
*Last Modified by: Kelly McGuinn
*Last Modified on: 3/6/15
*Assignment/Project: A290 Android Developement
*Part of: Final App
**/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SalonFinish extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.salon_finish);
	
	View cont = findViewById(R.id.cont);
    cont.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.cont) {
				Intent i = new Intent(this, MainActivity.class);
				startActivity(i);
		
	} 

}
	}



