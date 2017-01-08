package com.wordpress.shloknangia.wargame.parsers;

import com.wordpress.shloknangia.wargame.King;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHLOK on 07-01-2017.
 */

public class BattleJSONParser {
    public static List<King> parseFeed(String content){
        try{
            JSONArray ar = new JSONArray(content);
            List<King> kingList = new ArrayList<>();

            for(int i = 0;i<ar.length();i++){
                int n=400,k = 32;
                boolean isfirstBattleOfK1 = true;
                boolean isfirstBattleOfK2 = true;
                King kingA = new King(),kingB = new King();
                JSONObject obj = ar.getJSONObject(i);
                String attacker,defender;
                attacker = obj.getString("attacker_king");
                defender = obj.getString("defender_king");

                for(King tempking : kingList){
                    if(tempking.getName().equals(attacker)){
                        kingA = tempking;
                        isfirstBattleOfK1 = false;
                    }
                   if(tempking.getName().equals(defender)){
                        kingB = tempking;
                        isfirstBattleOfK2 = false;
                    }
                }

                String result;
                result = obj.getString("attacker_outcome");
                kingA.setName(attacker);
                kingB.setName(defender);
                if(result.equals("win")){
                    kingA.BattleWon();
                    kingA.setStrength("Attack");
                    kingB.BattleLost();
                }else if(result.equals("loss")){
                    kingA.BattleLost();
                    kingB.BattleWon();
                    kingB.setStrength("Defence");
                }
                double r1,r2,R1,R2,e1,e2,s1,s2,newR1,newR2;
                if(isfirstBattleOfK1) {
                    r1 = 400;
                }else{
                    r1 = kingA.getHighestRating();
                }
                if(isfirstBattleOfK2){
                    r2 = 400;
                }else{
                    r2 = kingB.getHighestRating();
                }

                R1 = Math.pow(10,(r1/n));
                R2 = Math.pow(10,(r2/n));

                e1 = R1/(R1+R2);
                e2 = R2/(R1+R2);

                if(result.equals("win")){
                    s1 = 1;s2 =0;
                }
                else if(result.equals("loss")){
                    s1 = 0;s2 = 1;
                }
                else{
                    s1 = 0.5;s2 = 0.5;
                }
                newR1 = r1 + k*(s1 - e1);
                newR2 = r2 + k*(s2 - e2);
                kingA.setHighestRating(newR1);
                kingB.setHighestRating(newR2);

                if(isfirstBattleOfK1)
                    kingList.add(kingA);
                if(isfirstBattleOfK2)
                    kingList.add(kingB);

            }
            return kingList;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
