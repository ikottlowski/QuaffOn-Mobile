/*FirstRealAppGameActivity.java
 * 
 * Created by: Kelly McGuinn
 * Created on: 2/25/15
 * Last Modified by: Kelly McGuinn
 * Last Modified on: 2/25/15
 * Assignment/Project: A290 Android Development
 * Part of: MyFirstRealApplication
 */

package edu.indiana.a290firstrealapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

public class FirstRealAppPuzzleView extends View {
	private static final String TAG = "FirstRealAppPuzzleView";
	private final FirstRealAppGameActivity game;
	public FirstRealAppPuzzleView(Context context) {
	super(context);
	this.game = (FirstRealAppGameActivity) context;
	setFocusable(true);
	setFocusableInTouchMode(true);
	}
	//We have more to fill in here
	
	private float width; //width of one tile
	private float height; //height of one tile
	private int selX; //X index of selection
	private int selY; //Y index of selection
	private final Rect selRect = new Rect();
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			width = w/9f;
			height = h/9f;
			getRect(selX, selY, selRect);
			Log.d(TAG, "onSizeChanged: width " + width + ", height " + height);
			super.onSizeChanged(w, h, oldw, oldh);
	}
	
	private void getRect(int x, int y, Rect rect) {
	
		rect.set((int) (x * width), (int) (y * height),
					(int) (x * width + width), (int) (y * height + height));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
	//Draw the background first
	Paint background = new Paint();
	background.setColor(getResources().getColor(R.color.PuzzleBackground));
	canvas.drawRect(0, 0, getWidth(), getHeight(), background);
	//Draw the board
		//First, define the colors for the grid lines.
	Paint dark = new Paint();
	dark.setColor(getResources().getColor(R.color.PuzzleDark));
	Paint hilite = new Paint();
	hilite.setColor(getResources().getColor(R.color.PuzzleHiLite));
	Paint light = new Paint();
	light.setColor(getResources().getColor(R.color.PuzzleLight));
	
		//Second, draw the minor grid lines (order is important)
	for (int i =0; i < 9; i++){
		canvas.drawLine(0, i * height, getWidth(), i * height, light);
		canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilite);
		canvas.drawLine(i * width, 0, i * width, getHeight(), light);
		canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilite);
	}
	
		//Third, draw the major grid lines (order is important)
	for (int i = 0; i < 9; i++) {
			if(i % 3 != 0)
				continue;
			canvas.drawLine(0, i * height, getWidth(), i * height, dark);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilite);
			canvas.drawLine(i * width, 0, i * width, getHeight(), dark);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilite);
	}
	//puzzle = getPuzzle(diff);
	//calculateUsedTiles();
	//Draw the numbers, after the game difficulty is selected
	//Draw the hints during the game
	//Draw the tile selected, so it is clearly distinguished from the other tiles
	}

	}

