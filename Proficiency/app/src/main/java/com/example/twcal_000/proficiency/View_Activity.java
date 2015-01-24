package com.example.twcal_000.proficiency;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class View_Activity extends ActionBarActivity {

    EditText firstName, lastName, email, phone, age;
    SQLiteDemoAdapter Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_);

        firstName=(EditText) findViewById(R.id.fName);
        lastName=(EditText) findViewById(R.id.lName);
        email=(EditText) findViewById(R.id.email);
        phone=(EditText) findViewById(R.id.phone);
        age=(EditText) findViewById(R.id.age);

        Database = new SQLiteDemoAdapter(this);
    }

    public void submit(View view) {
        String FirstName = firstName.getText().toString();
        String LastName = lastName.getText().toString();
        String Email = email.getText().toString();
        String Phone = phone.getText().toString();
        String Age = age.getText().toString();

        Database.insertData(FirstName, LastName, Email, Phone, Age);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
