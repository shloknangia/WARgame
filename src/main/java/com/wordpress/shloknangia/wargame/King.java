package com.wordpress.shloknangia.wargame;

/**
 * Created by SHLOK on 07-01-2017.
 */

public class King {
    private String name;
    private double highestRating;
    private int battlesWon;
    private int battleslost;
    private String strength;


    public void BattleWon(){
        this.battlesWon++;
    }

    public void BattleLost(){
        this.battleslost++;
    }



    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public int getBattleslost() {
        return battleslost;
    }

    public void setBattleslost(int battleslost) {
        this.battleslost = battleslost;
    }

    public int getBattlesWon() {
        return battlesWon;
    }

    public void setBattlesWon(int battlesWon) {
        this.battlesWon = battlesWon;
    }

    public double getHighestRating() {
        return highestRating;
    }

    public void setHighestRating(double highestRating) {
        this.highestRating = highestRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
