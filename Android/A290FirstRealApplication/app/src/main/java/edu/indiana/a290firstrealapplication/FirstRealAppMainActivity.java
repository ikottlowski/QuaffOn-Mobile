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
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.view.MenuInflater;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;


public class FirstRealAppMainActivity extends Activity implements OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_real_app_main);
        
        View ContinueButton = findViewById(R.id.ContinueButton);
        ContinueButton.setOnClickListener(this);
        
        View NewButton = findViewById(R.id.NewButton);
        NewButton.setOnClickListener(this);
        
        View AboutButton = findViewById(R.id.AboutButton);
        AboutButton.setOnClickListener(this);
        
        View ExitButton = findViewById(R.id.ExitButton);
        ExitButton.setOnClickListener(this);
        
    }

    public void onClick(View v){
    	switch (v.getId()) {
    	case R.id.AboutButton:
    		Intent i = new Intent(this, FirstRealAppAboutActivity.class);
    		startActivity(i);
    		break;
    		/*More buttons will/can go here as needed*/
    	case R.id.NewButton:
    		openNewGameDialog();
    		break;
    	case R.id.ExitButton:
    		finish();
    		break;
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_first_real_app_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
        case R.id.SettingsMenu:
        	startActivity (new Intent(this, FirstRealAppPrefsActivity.class));
        	return true;
 /* We can add more items to this switch-case construct, if and when we need them*/
        }
        return false;
    }
    
    private static final String TAG="SudokuMainActivity";
    
    private void openNewGameDialog(){
    	new AlertDialog.Builder(this)
    		.setTitle(R.string.NewGameTitle)
   /* If you have an icon you added and you want to have it appear as part
    * of this dialog, add this next line*/
    		.setIcon(R.drawable.ic_launcher)
    		
    		.setItems(R.array.difficulty,
    		new android.content.DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialoginterface,
    						int i) {
    					startGame(i);
    				}
    		})
    		.show();
    }
    
    private void startGame(int i){
    	Log.d(TAG, "Clicked on " + i);
    	/*Start game here...*/
		Intent intent = new Intent(this, FirstRealAppGameActivity.class);
		intent.putExtra(FirstRealAppGameActivity.KEY_DIFFICULTY, i);
		startActivity(intent);
		}
}
