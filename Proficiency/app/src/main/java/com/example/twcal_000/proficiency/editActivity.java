package com.example.twcal_000.proficiency;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Ike on 1/26/2015.
 */
public class editActivity extends ActionBarActivity {

    static EditText firstName, lastName, email, phone, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_page);

        firstName = (EditText) findViewById(R.id.fName);
        firstName.setText(verifyActivity.fName.getText());

        lastName = (EditText) findViewById(R.id.lName);
        lastName.setText(verifyActivity.lName.getText());

        email = (EditText) findViewById(R.id.email);
        email.setText(verifyActivity.email.getText());

        phone = (EditText) findViewById(R.id.phone);
        phone.setText(verifyActivity.phone.getText());

        age = (EditText) findViewById(R.id.age);
        age.setText(verifyActivity.age.getText());
    }

    public void next(View v){
        Intent verifyPage= new Intent(this, verifyActivity.class);
        startActivity(verifyPage);
    }
}
