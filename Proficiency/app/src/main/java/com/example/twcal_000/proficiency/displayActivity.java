package com.example.twcal_000.proficiency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ike on 1/26/2015.
 */
public class displayActivity extends ActionBarActivity {

    static TextView firstName, lastName, email, phone, age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_page);

        firstName = (TextView) findViewById(R.id.fNameView);
        firstName.setText(verifyActivity.fName.getText());

        lastName = (TextView) findViewById(R.id.lNameView);
        lastName.setText(verifyActivity.lName.getText());

        email = (TextView) findViewById(R.id.emailView);
        email.setText(verifyActivity.email.getText());

        phone = (TextView) findViewById(R.id.phoneView);
        phone.setText(verifyActivity.phone.getText());

        age = (TextView) findViewById(R.id.ageView);
        age.setText(verifyActivity.age.getText());

    }

    public void goToEditPage(View v){
        Intent editPage= new Intent(this, editActivity.class);
        startActivity(editPage);
    }

    public void FetchLastData(View v){
        Message.message(this, verifyActivity.Database.getData());
    }

    public void Delete(View v){
        verifyActivity.Database.deleteRow();
    }
}