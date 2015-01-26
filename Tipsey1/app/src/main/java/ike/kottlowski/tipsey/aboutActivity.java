package ike.kottlowski.tipsey;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;

public class aboutActivity extends Activity implements OnClickListener 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tipsey_about);

		//Initializes the click listener for each button
		View homeButton = findViewById(R.id.homeButton);
		homeButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()){
		case R.id.homeButton:
			Intent homeActivity = new Intent(this, TipseyMainActivity.class); 
			startActivity(homeActivity); 
			break;

		}

	}

}

