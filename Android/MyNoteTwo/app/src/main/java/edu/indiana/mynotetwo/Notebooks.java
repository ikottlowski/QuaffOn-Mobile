//Tyler Leeth
//Note Taking App
//A290 Android Final Project

package edu.indiana.mynotetwo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.os.Build;

public class Notebooks extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebooks);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.notesLayout, new PlaceholderFragment())
                    .commit();
        }
        
        
        showNotebooks();
        
        
        
    }
    
    private void showNotebooks() {
		
    	LinearLayout linLay = (LinearLayout) findViewById(R.id.nb_linlay);	
    	
/*    	Gives null pointer exception... Should clear buttons
 * 
 * 		if (linLay.getChildCount() > 0) {
    	    linLay.removeAllViews(); 
    	}
    	*/
    	
    	String query= "SELECT * FROM notebook;";
    	
    	/* Create Database if it doesnt already exist */
    	SQLiteDatabase myDb = createDb();
    	
    	Cursor c = myDb.rawQuery(query, null);
    	
    	int count = c.getCount();
 
    	//loop over queried data with cursor object (c)
    	for (int i=0; i < count; i++) {
    		if (i==0) {
    			c.moveToFirst();
    		} else {
    			c.moveToNext();
    		}
    		Button btn = new Button(this);
    		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    		btn.setText(c.getString(c.getColumnIndex("name")));
    		Log.d("notebook button text set to", c.getString(c.getColumnIndex("name")));
    		btn.setId(i);
    		
    		btn.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					int id = v.getId();
					
					/*Start notes intent and pass it the notebook id */
					Intent myIntent = new Intent(Notebooks.this, Notes.class);
					myIntent.putExtra("id",id+"");
					startActivity(myIntent);
					
				}
    		});
    		
    		
    		linLay.addView(btn);
    		
    		
    		
    	}
		
	}

	protected SQLiteDatabase createDb() {
    	SQLiteDatabase myDb = openOrCreateDatabase("MyNotes", Context.MODE_PRIVATE, null);
    	String sql = "CREATE TABLE IF NOT EXISTS note(id integer primary key autoincrement, name varchar(25), type varchar(6), contents text, nbid integer);";
    	myDb.execSQL(sql);
    	
    	sql = "CREATE TABLE IF NOT EXISTS notebook(id integer primary key autoincrement, name varchar(25));";
    	myDb.execSQL(sql);
    	
    	return myDb;
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notebooks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        if (id == R.id.action_newNotebook) {
        	
        	Log.d("Nav", "'New Notebook' Actionbar button clicked");
        	
        	
        	/* Show Alert asking for name for new notebook */
        	AlertDialog.Builder alert = new AlertDialog.Builder(this);
        	
        	alert.setTitle("New Notebook");
        	alert.setMessage("Please name your new notebook:");
        	final EditText input = new EditText(this);
        	alert.setView(input);
        	
        	alert.setPositiveButton("Create", new DialogInterface.OnClickListener() {
        		
        		public void onClick(DialogInterface dialog, int whichButton) {
        			/* Get Database */
        			SQLiteDatabase myDb = createDb();
        			
        			/* Get Entered Name for New note and insert into DB */
        			String newNoteName = input.getText().toString();
        			String sql = "INSERT INTO notebook(name) Values(\"" + newNoteName + "\");";
        			myDb.execSQL(sql);
        			Log.d("DB", "New Notebook Inserted");
        			
        			showNotebooks();
        			
        			
        			
        		}
        		
        	});
        	alert.show();
        	
        	
        	
        	
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
            View rootView = inflater.inflate(R.layout.fragment_notebooks, container, false);
            return rootView;
        }
    }

}
