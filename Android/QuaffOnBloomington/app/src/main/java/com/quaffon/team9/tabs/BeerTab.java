package com.quaffon.team9.tabs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.quaffon.team9.ListView.JSONParser;
import com.quaffon.team9.quaffonbloomington.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ike on 4/2/2015.
 */
public class BeerTab extends Fragment {
    ListView list;
    TextView brewery;
    TextView type;
    TextView abv;
    TextView ibu;
    TextView style;
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
    View v = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.beer_tab, container, false);
        brewery = (TextView) v.findViewById(R.id.brewery);
        beerlist = new ArrayList<HashMap<String, String>>();
        new JSONParse().execute();
        return v;
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            brewery = (TextView) v.findViewById(R.id.brewery);
            type = (TextView) v.findViewById(R.id.typeofbeer);
            abv = (TextView) v.findViewById(R.id.abv);
            ibu = (TextView) v.findViewById(R.id.ibu);
            style = (TextView) v.findViewById(R.id.styleofbeer);

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Let's See Whats on ap...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            return jParser.getJSONFromUrl(url);
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
                    String brewery = "Brewery: " + c.getString(TAG_BREWERY);
                    String type = "Name: " + c.getString(TAG_TYPE_OF_BEER);
                    String abv = "ABV: " + c.getString(TAG_ABV);
                    String ibu = "IBU: " + c.getString(TAG_IBU);
                    String style = "Style: " + c.getString(TAG_STYLE_OF_BEER);
                    // Adding value HashMap key => value
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(TAG_BREWERY, brewery);
                    map.put(TAG_TYPE_OF_BEER, type);
                    map.put(TAG_ABV, abv);
                    map.put(TAG_IBU, ibu);
                    map.put(TAG_STYLE_OF_BEER, style);
                    beerlist.add(map);
                    list = (ListView) v.findViewById(R.id.list);
                    ListAdapter adapter = new SimpleAdapter(getActivity(), beerlist,
                            R.layout.list_v,
                            new String[]{TAG_BREWERY,
                                    TAG_TYPE_OF_BEER,
                                    TAG_ABV,
                                    TAG_IBU,
                                    TAG_STYLE_OF_BEER}, new int[]{
                            R.id.brewery, R.id.typeofbeer, R.id.abv, R.id.ibu, R.id.styleofbeer});
                    list.setAdapter(adapter);
                }
                    /*
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            Toast.makeText(BeerTab.this, "You Clicked at " + beerlist.get(+position).get("name"), Toast.LENGTH_SHORT).show();
                        }
                    });*/
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}