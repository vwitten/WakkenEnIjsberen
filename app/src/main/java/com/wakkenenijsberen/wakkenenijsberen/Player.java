package com.wakkenenijsberen.wakkenenijsberen;

import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anferney on 7-12-2016.
 */
public class Player implements Serializable {
    private int playerID;
    private String name;
    private int amountOFDobbles;
    private String currentLevel;
    private Boolean pinguïnsUse;
    private ArrayList<Score> scores;

    public Player(String _name, int _amountOfDobbles, String _currentLevel, boolean _pinguïnsUse){
        this.name = _name;
        this.amountOFDobbles = _amountOfDobbles;
        this.currentLevel = _currentLevel;
        this.pinguïnsUse = _pinguïnsUse;
        this.scores = new ArrayList<Score>();
    }

    public Player(int _playerID, String _name, int _amountOfDobbles, String _currentLevel, boolean _pinguïnsUse){
        this.playerID = _playerID;
        this.name = _name;
        this.amountOFDobbles = _amountOfDobbles;
        this.currentLevel = _currentLevel;
        this.pinguïnsUse = _pinguïnsUse;
        this.scores = new ArrayList<Score>();
    }

    public void AddScore(Score _score){
        scores.add(_score);

    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public int getAmountOfDobbles(){
        return amountOFDobbles;
    }

    public void setAmountOFDobbles(int _amountOfDobbles){
        this.amountOFDobbles = _amountOfDobbles;
    }

    public String getCurrentLevel(){
        return currentLevel;
    }

    public void setCurrentLevel(String _currentLevel){
        this.currentLevel = _currentLevel;
    }

    public boolean getPinguïnsUse(){
        return pinguïnsUse;
    }

    public void setPinguïnsUse(boolean _pinguïnsuse){
        this.pinguïnsUse = _pinguïnsuse;
    }
}
