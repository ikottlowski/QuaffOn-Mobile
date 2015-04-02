package com.quaffon.team9.listviewdemo;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import quaffon.listview.library.JSONParser;

public class MainActivity extends Activity {
    ListView list;
    TextView brewery;
    TextView type;
    TextView abv;
    TextView ibu;
    TextView style;
    Button Btngetdata;
    ArrayList<HashMap<String, String>> beerlist = new ArrayList<HashMap<String, String>>();
    //URL to get JSON Array
    private static String url = "http://cgi.soic.indiana.edu/~team9/android/android_beer_list.php";
    //JSON Node Names
    private static final String TAG_BEER = "beer";
    private static final String TAG_BREWERY = "brewery";
    private static final String TAG_TYPE_OF_BEER = "type";
    private static final String TAG_ABV = "abv";
    private static final String TAG_IBU = "ibu";
    private static final String TAG_STYLE_OF_BEER = "style";
    JSONArray beer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beerlist = new ArrayList<HashMap<String, String>>();
        Btngetdata = (Button) findViewById(R.id.getdata);
        Btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONParse().execute();
            }
        });
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            brewery = (TextView) findViewById(R.id.brewery);
            type = (TextView) findViewById(R.id.typeofbeer);
            abv = (TextView) findViewById(R.id.abv);
            ibu = (TextView) findViewById(R.id.ibu);
            style = (TextView) findViewById(R.id.styleofbeer);

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
                // Getting JSON Array from URL
                beer = json.getJSONArray(TAG_BEER);
                for (int i = 0; i < beer.length(); i++) {
                    JSONObject c = beer.getJSONObject(i);
                    // Storing  JSON item in a Variable
                    String brewery = c.getString(TAG_BREWERY);
                    String type = c.getString(TAG_TYPE_OF_BEER);
                    String abv = c.getString(TAG_ABV);
                    String ibu = c.getString(TAG_IBU);
                    String style = c.getString(TAG_STYLE_OF_BEER);
                    // Adding value HashMap key => value
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(TAG_BREWERY, brewery);
                    map.put(TAG_TYPE_OF_BEER, type);
                    map.put(TAG_ABV, abv);
                    map.put(TAG_IBU, ibu);
                    map.put(TAG_STYLE_OF_BEER, style);
                    beerlist.add(map);
                    list = (ListView) findViewById(R.id.list);
                    ListAdapter adapter = new SimpleAdapter(MainActivity.this, beerlist,
                            R.layout.list_v,
                            new String[]{TAG_BREWERY, TAG_TYPE_OF_BEER, TAG_ABV, TAG_IBU, TAG_STYLE_OF_BEER}, new int[]{
                            R.id.brewery, R.id.typeofbeer, R.id.abv, R.id.ibu, R.id.styleofbeer});
                    list.setAdapter(adapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            Toast.makeText(MainActivity.this, "You Clicked at " + beerlist.get(+position).get("name"), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}