//Tyler Leeth
//Note Taking App
//A290 Android Final Project

package edu.indiana.mynotetwo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Build;

public class Notes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.notesLayout, new PlaceholderFragment()).commit();
		}
		
		
		/* Get Notebook ID from intent */
		Intent intent = getIntent();
		String id = intent.getStringExtra("id");
		Integer id_asInt = Integer.valueOf(id);
		
		//Increment ID because it came from i in for loop in Notebooks which started at 0.. needs to be incremented to match notebook id field in DB
		id_asInt++;
		Log.d("INTENT EXTRA", id_asInt.toString());
		
		/* Get title of current notebook with id */
		SQLiteDatabase myDb = openOrCreateDatabase("MyNotes", Context.MODE_PRIVATE, null);
    	String sql = "Select * FROM notebook WHERE id= " + id_asInt.toString() + ";"; 
		
    	//get title of current notebook
    	Cursor c = myDb.rawQuery(sql, null);
    	c.moveToFirst();
    	String nbName= c.getString(c.getColumnIndex("name"));
    	Log.d("DB","Current NB: "+nbName);
    	
    	//Set title of notebook 
    	TextView title = (TextView) findViewById(R.id.currNotebookText);
    	title.setText("Notebook: " + nbName);
    	
    
    	
    	/* Display notes on page */
    	
    		
    	
    	String query = "Select * from note;";
    	
    	Cursor cs = myDb.rawQuery(query, null);
    	
    	int length = cs.getCount();
 
    	
    	for (int i=0; i < length; i++) {
    		if (i==0) {
    			cs.moveToFirst();
    		} else {
    			cs.moveToNext();
    		}
    		Button noteBtn = new Button(this);
    		noteBtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    		
    		
    		Log.d("trace note name",cs.getString(cs.getColumnIndex("name")));
    		
    		noteBtn.setText(cs.getString(cs.getColumnIndex("name")));
    		noteBtn.setId(i);
    		
    		noteBtn.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					int id = v.getId();
					
					//Start notes intent and pass it the notebook id 
					Intent myIntent = new Intent(Notes.this, ShowNote.class);
					myIntent.putExtra("id",id+"");
					startActivity(myIntent);
					
				}
    		});
    		
    		LinearLayout layout = (LinearLayout) findViewById(R.id.notesLayout);
    		
    		layout.addView(noteBtn);
    		
    		
    		
    	}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notes, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (id == R.id.action_newNote) {
			
			Log.d("NAV","New Note '+' Clicked");
			Intent intent = new Intent(Notes.this, CreateNewNote.class);
			Log.d("NAV","INTENT INIT");
			startActivity(intent);
			Log.d("NAV","INTENT STARTED");
			finish();
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
			View rootView = inflater.inflate(R.layout.fragment_notes,
					container, false);
			return rootView;
		}
	}

}
