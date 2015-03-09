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

public class FirstRealAppAboutActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_real_app_about);
	}
}
