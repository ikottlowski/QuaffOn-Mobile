package edu.indiana.finalapp;
 
/*MallActivity.java
*
*Created by: Kelly McGuinn
*Last Modified by: Kelly McGuinn
*Last Modified on: 3/6/15
*Assignment/Project: A290 Android Developement
*Part of: Final App
**/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

 
public class MallActivity extends Activity implements OnClickListener {
 
  private CheckBox dress1, purse1, shoes1, necklace1;
  private Button checkprice1;
 
  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.mall_activity);
 
	View checkprice = findViewById(R.id.checkprice);
    checkprice.setOnClickListener(this);
	
	addListenerOnButton();
  }
 
 
  public void addListenerOnButton() {
 
	dress1 = (CheckBox) findViewById(R.id.dress);
	purse1 = (CheckBox) findViewById(R.id.purse);
	shoes1 = (CheckBox) findViewById(R.id.shoes);
	necklace1 = (CheckBox) findViewById(R.id.necklace);
	checkprice1 = (Button) findViewById(R.id.checkprice);
  }
 
	//checkprice.setOnClickListener(new OnClickListener() {
 
          //Run when button is clicked
	  @Override
	  public void onClick(View v) {
		  int id = v.getId();
		  if (id == R.id.checkprice && dress1.isChecked() && purse1.isChecked() 
				  && !shoes1.isChecked() && !necklace1.isChecked()) {
			  Intent i = new Intent(this, MallFinish.class);
			  startActivity(i);
		  }
		  else if (id == R.id.checkprice && necklace1.isChecked() && purse1.isChecked()
				      && !dress1.isChecked() && !shoes1.isChecked()) {
			  Intent i = new Intent(this, MallFinish.class);
			  startActivity(i);
		  }
		  else if (id == R.id.checkprice && shoes1.isChecked() && purse1.isChecked()
				       && !dress1.isChecked() && !necklace1.isChecked()) {
			  Intent i = new Intent(this, MallFinish.class);
			  startActivity(i);
		  }
		  else {
		  Intent i = new Intent(this, SalonWrong.class);
		  		startActivity(i);
		  }
	}
  }
	
  


