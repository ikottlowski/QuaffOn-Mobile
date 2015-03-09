//Tyler Leeth
//Note Taking App
//A290 Android Final Project

package edu.indiana.mynotetwo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class ShowNote extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_note);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.notesLayout, new PlaceholderFragment()).commit();
		}
		
		
		
		String passedName = "a title";
		String passedText = "some note content";
		
		
		TextView noteTitle = (TextView) findViewById(R.id.showNoteTitle);
		TextView noteText = (TextView) findViewById(R.id.showNoteText);
		noteTitle.setText(passedName);
		noteText.setText(passedText);
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_note, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_show_note,
					container, false);
			return rootView;
		}
	}

}
