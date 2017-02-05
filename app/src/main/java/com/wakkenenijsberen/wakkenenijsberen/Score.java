package com.wakkenenijsberen.wakkenenijsberen;

import java.sql.Time;

/**
 * Created by anferney on 7-12-2016.
 */
public class Score {
    private int scoreID;
    private double scorePoints;
    private int scoreWrong;
    private Time duration;
    private int amountOfDices;
    private int levelID;
    private boolean pinguinUse;
    private int playerID;
    private String playerName;

    //Constructor with ScoreID
    public Score(int _scoreID, double _scorePoints, int _scoreWrong, Time _duration, int _amountOfDices, int _levelID, boolean _pinguinsUse, int _playerID){
        this.scoreID = _scoreID;
        this.scorePoints = _scorePoints;
        this.scoreWrong = _scoreWrong;
        this.duration = _duration;
        this.amountOfDices = _amountOfDices;
        this.levelID = _levelID;
        this.pinguinUse = _pinguinsUse;
        this.playerID = _playerID;
    }

    //Constructor without ScoreID
    public Score(double _scorePoints, int _scoreWrong, Time _duration, int _amountOfDices, int _levelID, boolean _pinguinsUse, int _playerID){
        this.scorePoints = _scorePoints;
        this.scoreWrong = _scoreWrong;
        this.duration = _duration;
        this.amountOfDices = _amountOfDices;
        this.levelID = _levelID;
        this.pinguinUse = _pinguinsUse;
        this.playerID = _playerID;
    }
    public Score(String _playername, double _scorePoints, int _scoreWrong, Time _duration, int _amountOfDices, int _levelID, boolean _pinguinsUse, int _playerID){
        this.scorePoints = _scorePoints;
        this.scoreWrong = _scoreWrong;
        this.duration = _duration;
        this.amountOfDices = _amountOfDices;
        this.levelID = _levelID;
        this.pinguinUse = _pinguinsUse;
        this.playerID = _playerID;
        this.playerName = _playername;
    }

    public int getScoreID() {
        return scoreID;
    }

    public void setScoreID(int scoreID) {
        this.scoreID = scoreID;
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public double getScorePoints() {
        return scorePoints;
    }

    public void setScorePoints(double scoreCorrect) {
        this.scorePoints = scoreCorrect;
    }

    public int getScoreWrong() {
        return scoreWrong;
    }

    public void setScoreWrong(int scoreWrong) {
        this.scoreWrong = scoreWrong;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getAmountOfDices() {
        return amountOfDices;
    }

    public void setAmountOfDices(int amountOfDices) {
        this.amountOfDices = amountOfDices;
    }

    public boolean getIsPinguinUse() {
        return pinguinUse;
    }

    public void setPinguinUse(boolean pinguinUse) {
        this.pinguinUse = pinguinUse;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
