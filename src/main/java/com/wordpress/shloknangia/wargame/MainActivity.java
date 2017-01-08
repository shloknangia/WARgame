package com.wordpress.shloknangia.wargame;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.shloknangia.wargame.parsers.BattleJSONParser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<King> kingList;
    TextView textView;
    ListView listView;
    public int count =0;
    public static final String TAG = "Retrived string:  ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        textView = (TextView)findViewById(R.id.count);

        if(isOnline()){
        requestData("http://starlord.hackerearth.com/gotjson");
        }else{
            Toast.makeText(this,"No Internet",Toast.LENGTH_SHORT).show();
        }

    }

    public void updateTextBox(View view){
        if(isOnline()){
            requestData("http://starlord.hackerearth.com/gotjson");
        }else{
            Toast.makeText(this,"No Internet",Toast.LENGTH_SHORT).show();
        }

    }
    protected Boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting()){
           return true;
        }
        else{
            return false;
        }
    }
    private void requestData(String uri){
        MyTask task = new MyTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,uri,"Param 2","Param3");
    }
    void updatedisplay(){
        KingAdapter adapter = new KingAdapter(this,R.layout.list_item,kingList);
        listView.setAdapter(adapter);
        for(King ki: kingList){
            count++;
        }
        textView.setText(String.valueOf(count)+" Kings");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,Details.class);
//                intent.putExtra("id",i);
                King kking = kingList.get(i);
                intent.putExtra("name",kking.getName());
                intent.putExtra("rating",kking.getHighestRating());
                intent.putExtra("battles_won",kking.getBattlesWon());
                intent.putExtra("battles_lost",kking.getBattleslost());
                intent.putExtra("strength",kking.getStrength());
                startActivity(intent);
            }
        });
    }
    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            super.onPostExecute(s);
            Log.d(TAG, s);
            kingList = BattleJSONParser.parseFeed(s);
            updatedisplay();

        }

//        protected void OnPreExecute() {
//            super.onPreExecute();
////            updatedisplay("starting task");
////            if (task.size() == 0) {
////                pb.setVisibility(view.VISIBLE)//pb is progress bar
////            }
////            tasks.add(this);
//        }
        protected String doInBackground(String... params) {
            String content = HttpManager.getData(params[0]);
            return content;

        }

//        protected void OnPostExecute(String result) {
//            super.onPostExecute(result);
//            Log.d(TAG, result);
//            kingList = BattleJSONParser.parseFeed(result);
//            updatedisplay();
//
//
////            tasks.remove(this);
////            if (task.size() == 0) {
////                pb.setVisibility(view.IN VISIBLE)
////            }
//        }

    }
}