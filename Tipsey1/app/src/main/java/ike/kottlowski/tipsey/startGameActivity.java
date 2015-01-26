package ike.kottlowski.tipsey;

import android.graphics.drawable.ShapeDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
//import android.util.Log;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;

//import android.util.Log;

public class startGameActivity extends Activity implements SensorEventListener
{	
	// Creates the board for the game
	gameView BoardDrawableView = null;
	ShapeDrawable ball = new ShapeDrawable();
	ShapeDrawable hoop = new ShapeDrawable();

	private SensorManager sensorManager = null;
	Sensor accelerometer;

	public static int x = 360, y = 500;
	
	//private String TAG = "TIME";
	
	// Starts tracking the time once the Game has started
	static long startTime = System.currentTimeMillis();
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		//sets the variable sensorManager to access the sensor service in android
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		//sets the default sensor to be the accelerometer
		accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);	

		//sets the view to contain the Board
		BoardDrawableView = new gameView(this);
		setContentView(BoardDrawableView);
		
	}
	
	// Tracks which game in the series the user is in (sober / tipsey) as to display
	// the appropriate postDialog
	static int d = 0;
	static long deltaTime = 0;
	
	@Override
	public void onSensorChanged(SensorEvent event) 
	{
		// Variables that track the time of the game
		long endTime = 0;
		
		Intent postGame = new Intent(this, postGameActivity.class);
		
		// Throws the player from the game into the post game dialog
		// depending on the first or second play will depend on which dialog will be displayed
		if(gameView.i == 10){
			gameView.i = 0;
			if(d == 0){
				
				// Calculates the time spent playing current game
				endTime = System.currentTimeMillis();
				deltaTime = endTime - startTime;
				d += 1;
				startActivity(postGame);
				finish();
			
			} else {
				startActivity(postGame);
				finish();
				d = 0;
			}
		}
		
		// Controls the forward / backward movement, y axis
		if (event.values[1] < 4.5) {
			if (y < 0){
				y += 30;
			} else {
				y += -10;
			}

		} else if (event.values[1] > 5) {
			if (y > 1000){
				y += -30;
			} else {
				y += 10;
			}
		}

		// Controls the left / right movement, x axis
		if ( event.values[0] > .75 ){
			if (x < 0){
				x += 30;
			} else {
				x += -10;
			}
		} else if ( event.values[0] < -.75 ){
			if (x > 720){
				x += -30;
			} else {
				x += 10;
			}
		}
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) 
	{
		// I chose to not use this method
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		// Register this class as a listener for the accelerometer sensor
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
		
	}

	@Override
	protected void onStop()
	{
		// Unregister the listener
		sensorManager.unregisterListener(this);
		super.onStop();
		finish();
	}
}
