package com.example.twcal_000.proficiency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by Ike on 1/22/2015.
 */
public class SQLiteDemoAdapter {

    SQLiteDemo helper;
    public SQLiteDemoAdapter(Context context) {
        helper = new SQLiteDemo(context);
    }

    public void insertData(String fname, String lname, String email, String phone, String age) {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteDemo.fName, fname);
        contentValues.put(SQLiteDemo.lName, lname);
        contentValues.put(SQLiteDemo.email, email);
        contentValues.put(SQLiteDemo.phone, phone);
        contentValues.put(SQLiteDemo.age, age);

        db.insert(SQLiteDemo.TABLE_NAME, null, contentValues);
    }

    public void getData(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns={helper.UID, helper.fName, helper.lName, helper.email, helper.phone, helper.age};
        Cursor cursor= db.query(helper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext()){
            int cid = cursor.getInt(0);
            String fname = cursor.getString(1);
            String lname = cursor.getString(2);
            String email = cursor.getString(3);
            String phone = cursor.getString(4);
            String age = cursor.getString(5);
            buffer.append(cid+" "+fname+" "+lname+" "+email+" "+phone+" "+age+"\n");

        }
    }
    class SQLiteDemo extends SQLiteOpenHelper {

        //setting the database variables
        private static final String DATABASE_NAME = "demoDatabase";
        private static final String TABLE_NAME = "demoTable";
        private static final int DATABASE_VERSION = 1;

        //column names in the database table
        private static final String UID = "id";
        private static final String fName = "name";
        private static final String lName = "lName";
        private static final String email = "email";
        private static final String phone = "phone";
        private static final String age = "age";

        //queries for the database
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"( "+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ""+fName+" VARCHAR(255), "+lName+" VARCHAR(255), "+email+" VARCHAR(255), " +
                ""+phone+" VARCHAR(255), "+age+" VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE "+TABLE_NAME+" IF EXISTS";
        private Context context;

        public SQLiteDemo(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //CREATE TABLE demoTable
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }
}
