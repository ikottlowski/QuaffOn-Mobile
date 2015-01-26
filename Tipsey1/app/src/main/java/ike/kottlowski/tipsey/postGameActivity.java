package ike.kottlowski.tipsey;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.Activity;

public class postGameActivity extends Activity implements OnClickListener{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_tipsey_post_dialog);

		// Displays the time it took the game to finish
		TextView postGameTime = (TextView) findViewById(R.id.postGameText);
		postGameTime.setText("Your Time Was:\n " + stringFormater(startGameActivity.deltaTime));

		// Give functionality to the home button
		View homeButton = findViewById(R.id.okayButton);
		homeButton.setOnClickListener(this);
	}

	// Formats the time into a pleasant readable format
	@SuppressLint("DefaultLocale")
	public String stringFormater(long time) {
		int tens = (int) time % 1000;
		int seconds = (int) time / 1000;
		int minutes = seconds / 60;

		return String.format("%02d:%02d.%d", minutes, seconds, tens);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.okayButton: 
			finish();
			break; 

		}
	}

	@Override
	protected void onStop()
	{
		// Unregister the listener
		super.onStop();
		finish();
	}

}
