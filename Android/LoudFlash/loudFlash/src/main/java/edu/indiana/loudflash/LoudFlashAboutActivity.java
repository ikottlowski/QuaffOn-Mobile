package edu.indiana.loudflash;

/* LoudFlashAboutActivity.java
 * 
 * Contains java code for the About dialog
 * 
 * Created by: Artur Lukin
 * Created on: 3/2/15
 * Last modified by: Artur Lukin
 * Last modified on: 3/6/15
 * Assignment/Project: LoudFlash
 * Refers to loudflash_about.xml
 */


import android.app.Activity;
import android.os.Bundle;

public class LoudFlashAboutActivity extends Activity {
		@Override
		protected void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			setContentView(R.layout.loudflash_about);
		}
}