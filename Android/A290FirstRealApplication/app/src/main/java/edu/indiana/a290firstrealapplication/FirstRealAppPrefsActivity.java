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

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class FirstRealAppPrefsActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}

}
