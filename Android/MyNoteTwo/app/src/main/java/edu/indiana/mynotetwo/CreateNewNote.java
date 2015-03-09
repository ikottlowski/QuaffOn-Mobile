//Tyler Leeth
//Note Taking App
//A290 Android Final Project

package edu.indiana.mynotetwo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class CreateNewNote extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_note);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.notesLayout, new PlaceholderFragment()).commit();
		}
		
		/* Set Save Button Listener */
		View saveBtn =  findViewById(R.id.saveButton);
		
		
//		for some reason causes a runtime error
		saveBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 //Gather inputs 
				EditText name = (EditText) findViewById(R.id.editTextName);
				EditText contents = (EditText) findViewById(R.id.editTextContents);
				String newName = name.getText().toString();
				String newContents = contents.getText().toString();
				
				
				//notebook id hardcoded
				String nbid = "1";
				
				
				saveNoteAndReturn(newName, newContents, nbid);
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_new_note, menu);
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
	
	public void saveNoteAndReturn(String pName, String pContents, String pNbId) {
		/* Gather inputs */
		
		
		/* Insert inputs as note in DB */
		SQLiteDatabase myDb = openOrCreateDatabase("MyNotes", Context.MODE_PRIVATE, null);
    	String sql = "INSERT INTO note(name,type,contents, nbid) VALUES (" + pName + ",\"text\"," + pContents + "," + pNbId + ");";
    	
    	myDb.execSQL(sql);
		
    	
		/* Go Back to Notes Activity */
		Intent myIntent = new Intent(CreateNewNote.this, Notes.class);
		startActivity(myIntent);
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
			View rootView = inflater.inflate(R.layout.fragment_create_new_note,
					container, false);
			return rootView;
		}
	}

}
