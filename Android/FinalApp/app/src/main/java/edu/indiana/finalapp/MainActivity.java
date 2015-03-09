package edu.indiana.finalapp;

/*MainActivity.java
*
*Created by: Kelly McGuinn
*Last Modified by: Kelly McGuinn
*Last Modified on: 3/6/15
*Assignment/Project: A290 Android Developement
*Part of: Final App
**/



import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* hides the title bar */
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);
		
		View ibutton = findViewById(R.id.ibutton);
        ibutton.setOnClickListener(this);
        
        View salon = findViewById(R.id.salon);
        salon.setOnClickListener(this);
        
        View mall = findViewById(R.id.mall);
        mall.setOnClickListener(this);
        
	}
	
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.ibutton) {
			Intent i = new Intent(this, MainInstructions.class);
			startActivity(i);
		} else if (id == R.id.salon) {
			Intent i = new Intent(this, SalonActivity.class);
			startActivity(i);
		} else if (id == R.id.mall) {
			Intent i = new Intent(this, MallActivity.class);
			startActivity(i);
		}
		}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
