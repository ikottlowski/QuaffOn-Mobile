package ike.kottlowski.tipsey;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

//import android.util.Log;

public class gameView extends View 
{
	//private final String TAG = "Drawable";
	//oval size
	static final int width = 50;
	static final int height = 50;
	
	//hoop size
	static final int Hwidth = 100;
	static final int Hheight = 100;
	
	//hoop position
	int hoopXAxis = (int) ( Math.random() * 700 );
	int hoopYAxis = (int) ( Math.random() * 900 );

	public gameView(Context context) 
	{
		super(context);	
		setKeepScreenOn(true);
		
		ShapeDrawable ball = new ShapeDrawable(new OvalShape());
		
		ball.getPaint().setColor(0xff74AC23);
		ball.setBounds(startGameActivity.x, startGameActivity.y, startGameActivity.x + width, startGameActivity.y + height);
		
		ShapeDrawable hoop = new ShapeDrawable(new OvalShape());
		hoop.getPaint().setColor(0xff74AC23);
		hoop.setBounds(hoopXAxis, hoopYAxis, Hwidth, Hheight);
		
		
	}
	
	//calls for randomizing once the hoop is touched by the ball
	private void randomX(){
		hoopXAxis = (int) (Math.random() * 700);
	}
	
	private void randomY(){
		hoopYAxis = (int) ( Math.random() * 900 );
	}

	//on screen objects
	RectF oval;
	RectF hoop;
	
	//paint objects associated with on screen objects
	Paint ovalP = new Paint();
	Paint hoopP = new Paint();
	
	// i controls the number of hoops gone through
	static int i = 0;
	
	protected void onDraw(Canvas canvas)
	{
		
		oval = new RectF(startGameActivity.x, startGameActivity.y, startGameActivity.x + width, startGameActivity.y + height);
		hoop = new RectF(hoopXAxis, hoopYAxis, hoopXAxis + Hwidth, hoopYAxis + Hheight);
		
		ovalP.setColor(Color.BLUE);
		hoopP.setColor(Color.BLACK);
		
		// changes the area where the hoop appears, after it's been accessed by the ball
		// and tracks the number 
		canvas.drawOval(oval, ovalP);
		if (((hoopXAxis - startGameActivity.x) < 55) && ((hoopXAxis - startGameActivity.x) > -55) &&
				((hoopYAxis - startGameActivity.y) < 55) && ((hoopYAxis - startGameActivity.y) > -55)){
			randomX();
			randomY();
			hoop = new RectF(hoopXAxis, hoopYAxis, hoopXAxis + Hwidth, hoopYAxis + Hheight);
			i += 1;
		}
		
		canvas.drawOval(hoop, hoopP);
		invalidate();
	}

}
