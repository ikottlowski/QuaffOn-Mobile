package edu.indiana.finalapp;

/*MallFinish.java
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

public class MallFinish extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.mall_finish);
	
	View contmall = findViewById(R.id.contmall);
    contmall.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.contmall) {
				Intent i = new Intent(this, MainActivity.class);
				startActivity(i);
		
	} 

}
	}



