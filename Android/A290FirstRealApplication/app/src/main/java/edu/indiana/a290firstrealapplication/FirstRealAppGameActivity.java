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

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class FirstRealAppGameActivity extends Activity {
	
	private static final String TAG = "FirstRealAppGameActivity";
	
	public static final String KEY_DIFFICULTY =
			"edu.indiana.a290firstrealapplication.difficulty";
	
	public static final int DIFFICULTY_EASY = 0;
	public static final int DIFFICULT_MEDIUM = 1;
	public static final int DIFFICULTY_HARD = 2;
	
	private int puzzle[];
	
	private FirstRealAppPuzzleView puzzleView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
	
		int diff = getIntent().getIntExtra(KEY_DIFFICULTY,
				DIFFICULTY_EASY);
	 //puzzle = getPuzzle(diff);
	 //calculateUsedTiles();
	
	 puzzleView = new FirstRealAppPuzzleView(this);
	 setContentView(puzzleView);
	 puzzleView.requestFocus();
	}
	//We have more work to do here...

}
