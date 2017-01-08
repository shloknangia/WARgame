package com.wordpress.shloknangia.wargame;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SHLOK on 07-01-2017.
 */

public class KingAdapter extends ArrayAdapter<King> {

    private Context context;
    private List<King> kingList;


    public KingAdapter(Context context, int resource, List<King> objects){
        super(context, resource, objects);
        this.context = context;
        this.kingList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item,parent,false);
//        ie custom file name

        // to display item name
        King king = kingList.get(position);
        TextView kingName = (TextView)view.findViewById(R.id.name);
        TextView rating = (TextView)view.findViewById(R.id.rating) ;
        TextView battleStrength = (TextView)view.findViewById(R.id.battle_strength);
        if(king.getName().equals("")){
            kingName.setText("UNKNOWN KING");
        }else{
            kingName.setText(king.getName());
        }
        rating.append(String.valueOf((int)king.getHighestRating()));
        battleStrength.append(String.valueOf(king.getBattlesWon()));
        return view;
    }

}
