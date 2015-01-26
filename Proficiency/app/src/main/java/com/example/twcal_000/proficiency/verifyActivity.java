package com.example.twcal_000.proficiency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Ike on 1/26/2015.
 */
public class verifyActivity extends ActionBarActivity{

    static SQLiteDemoAdapter Database;
    static TextView fName, lName, email, phone, age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_page);

        fName = (TextView) findViewById(R.id.fNameTextView);
        fName.setText(mainActivity.firstName.getText());

        lName = (TextView) findViewById(R.id.lNameTextView);
        lName.setText(mainActivity.lastName.getText());

        email = (TextView) findViewById(R.id.emailTextView);
        email.setText(mainActivity.email.getText());

        phone = (TextView) findViewById(R.id.phoneTextView);
        phone.setText(mainActivity.phone.getText());

        age = (TextView) findViewById(R.id.ageTextView);
        age.setText(mainActivity.age.getText());

        Database = new SQLiteDemoAdapter(this);
    }

    public void goToEditPage(View v){
        Intent editPage= new Intent(this, editActivity.class);
        startActivity(editPage);
    }

    //submits the data to the database via insert statement
    public void submit(View v) {

        fName.setText(mainActivity.firstName.getText());
        lName.setText(mainActivity.lastName.getText());
        email.setText(mainActivity.email.getText());
        phone.setText(mainActivity.phone.getText());
        age.setText(mainActivity.age.getText());

        String FirstName = fName.getText().toString();
        String LastName = lName.getText().toString();
        String Email = email.getText().toString();
        String Phone = phone.getText().toString();
        String Age = age.getText().toString();

        Database.insertData(FirstName, LastName, Email, Phone, Age);

        Intent displayPage= new Intent(this, displayActivity.class);
        startActivity(displayPage);
    }
}
