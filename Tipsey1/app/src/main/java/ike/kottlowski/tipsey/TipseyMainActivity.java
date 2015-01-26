package ike.kottlowski.tipsey;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

public class TipseyMainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_tipsey_main);
		
		//Initializes the click listener for each button
		View startButton = findViewById(R.id.startButton);
		startButton.setOnClickListener(this);
		
		View aboutButton = findViewById(R.id.aboutButton);
		aboutButton.setOnClickListener(this);
		
		//May add later
		//View statsButton = findViewById(R.id.statsButton);
		//statsButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) { 
		case R.id.startButton: 
			Intent startGameActivity = new Intent(this, startGameActivity.class); 
			startActivity(startGameActivity); 
			break; 
			
		case R.id.aboutButton: 
			Intent aboutActivity = new Intent(this, aboutActivity.class); 
			startActivity(aboutActivity); 
			break; 
		/* May add later
		case R.id.statsButton: 
			Intent statsActivity = new Intent(this, statsActivity.class); 
			startActivity(statsActivity); 
			break;  
		*/
		}
		
	}

}
