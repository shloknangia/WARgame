package com.wordpress.shloknangia.wargame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;



public class Details extends AppCompatActivity {

    TextView name,rating,battleWon,battleLost,strength;
    String stringName,stringStrength,stringStrengthBattle;
    double receivedRating;
    int battles_won,battles_lost;
//    List<King> kingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = (TextView) findViewById(R.id.detail_name);
        rating = (TextView) findViewById(R.id.detail_rating);
        battleWon = (TextView)findViewById(R.id.battles_won);
        battleLost = (TextView)findViewById(R.id.battles_lost);
        strength = (TextView)findViewById(R.id.detail_strength);

        Bundle bundle = getIntent().getExtras();
        stringName = bundle.getString("name");
        receivedRating = bundle.getDouble("rating");
        battles_won = bundle.getInt("battles_won");
        battles_lost = bundle.getInt("battles_lost");
        stringStrength = bundle.getString("strength");
        if(stringName.equals("")){
           name.setText("UNKNOWN KING");
        }else{
            name.setText(stringName);
        }
        rating.setText(String.valueOf((int) receivedRating));
        battleWon.setText(String.valueOf(battles_won));
        battleLost.setText(String.valueOf(battles_lost));
        strength.setText(stringStrength);


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
