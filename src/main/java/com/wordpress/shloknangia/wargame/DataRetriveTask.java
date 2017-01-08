package com.wordpress.shloknangia.wargame;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by SHLOK on 07-01-2017.
 */

public class DataRetriveTask extends AsyncTask<String,String,String> {

    public static ArrayList<King> KingList;
    String json_url;
    public static final String TAG = "DataRetriveTask";

    @Override
    protected void onPreExecute() {
        KingList = new ArrayList<>();
        json_url = "http://starlord.hackerearth.com/gotjson";
    }
    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(json_url);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String buffer = br.readLine();
            while (buffer != null) {
                sb.append(buffer);
                buffer = br.readLine();
            }

            return sb.toString();
        } catch(Exception e){
            Log.d(TAG, "Error in doInBackground : " + e);
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(String s) {
        try {
            Log.d(TAG, s);
            JSONArray jsonArray = new JSONArray(s);

            int max = jsonArray.length();
            int count=0;

            String battlename,attacker,defender;
            JSONObject jo;

            while(count<max) {
                jo = jsonArray.getJSONObject(count);
                battlename = jo.getString("name");
                attacker = jo.getString("attacker_king");
                defender = jo.getString("defender_king");

                Log.d(TAG, "Battle Name : " + battlename);

//                KingList.add(new Stock(name, symbol, last_price, percent_change, gain));

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onPostExecute(s);
    }
}