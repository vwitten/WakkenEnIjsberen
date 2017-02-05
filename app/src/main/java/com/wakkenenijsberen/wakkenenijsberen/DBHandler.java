package com.wakkenenijsberen.wakkenenijsberen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WakkenDB.db";

    public static final String TABLE_PLAYER = "Player";
    public static final String COLUMN_PLAYERID = "PlayerID";
    public static final String COLUMN_PLAYER_NAME = "PlayerName";
    public static final String COLUMN_AMOUNT_OF_DOBBLES = "AmountOfDobbles";
    public static final String COLUMN_CURRENT_LEVEL = "CurrentLevel";
    public static final String COLUMN_PINGUÏNS_USE_P = "PinguïnsUseP";

    public static final String TABLE_LEVEL = "Level";
    public static final String COLUMN_LEVELID = "LevelID";
    public static final String COLUMN_LEVEL_DESCRIPTION = "LevelDescription";
    public static final String COLUMN_LEVEL_UNLOCKED = "LevelUnlocked";
    public static final String COLUMN_PLAYER_ID_L = "Player_ID_L";

    public static final String TABLE_SCORE = "Score";
    public static final String COLUMN_SCOREID = "ScoreID";
    public static final String COLUMN_DURATION = "Duration";
    public static final String COLUMN_LEVEL_ID_S = "Level_ID_S";
    public static final String COLUMN_NUMBER_OF_FAILS = "NumberOfFails";
    public static final String COLUMN_SCORE_POINTS = "ScorePoints";
    public static final String COLUMN_PINGUÏNS_USE = "PinguïnsUse";
    public static final String COLUMN_AMOUNT_OF_DICES = "AmountOfDices";
    public static final String COLUMN_PLAYER_ID_S = "Player_ID_S";


    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_PLAYER + "(" + COLUMN_PLAYERID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PLAYER_NAME
                + " TEXT," + COLUMN_AMOUNT_OF_DOBBLES + " INTEGER," + COLUMN_CURRENT_LEVEL + " TEXT," + COLUMN_PINGUÏNS_USE_P + " TEXT);";
        db.execSQL(CREATE_PLAYER_TABLE);

        String CREATE_LEVEL_TABLE = "CREATE TABLE " + TABLE_LEVEL + "(" + COLUMN_LEVELID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_LEVEL_DESCRIPTION + " TEXT," + COLUMN_LEVEL_UNLOCKED
                + " INTEGER," + COLUMN_PLAYER_ID_L + " INTEGER);";
        db.execSQL(CREATE_LEVEL_TABLE);

        String CREATE_SCORE_TABLE = "CREATE TABLE " + TABLE_SCORE + "(" + COLUMN_SCOREID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DURATION + " TEXT," + COLUMN_LEVEL_ID_S
                + " INTEGER," + COLUMN_NUMBER_OF_FAILS + " INTEGER," + COLUMN_SCORE_POINTS + " REAL," + COLUMN_PINGUÏNS_USE
                + " TEXT,"+ COLUMN_AMOUNT_OF_DICES + " INTEGER," + COLUMN_PLAYER_ID_S + " INTEGER);";
        db.execSQL(CREATE_SCORE_TABLE);

        /*
        String CREATE_PLAYER_SCORE_TABLE = "CREATE TABLE " + TABLE_PLAYER_SCORE + "(" + COLUMN_PLAYER_ID
                + " INTEGER," + COLUMN_SCORE_ID + " INTEGER);";
        db.execSQL(CREATE_PLAYER_SCORE_TABLE);
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_PLAYER);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_LEVEL);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_SCORE);
        //db.execSQL("DROP TABLE IF EXIST " + TABLE_PLAYER_SCORE);
        onCreate(db);
    }

    public Player CreatePlayer(Player objPlayer){
        //Creates a players and adds this to the database.
        ContentValues PlayerValues = new ContentValues();

        String name = objPlayer.getName();
        int amountOfDobbles = objPlayer.getAmountOfDobbles();
        String currentLevel = objPlayer.getCurrentLevel();
        boolean pinguïnsUse = objPlayer.getPinguïnsUse();

        PlayerValues.put(COLUMN_PLAYER_NAME, name);
        PlayerValues.put(COLUMN_AMOUNT_OF_DOBBLES,amountOfDobbles);
        PlayerValues.put(COLUMN_CURRENT_LEVEL, currentLevel);
        PlayerValues.put(COLUMN_PINGUÏNS_USE_P, String.valueOf(pinguïnsUse));

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PLAYER, null, PlayerValues);

        //Experimenting
        Player[] objPl = GetAllPlayers();
        Player objP = objPl[objPl.length - 1];

        //Gets playerID to create the levels
        int PlayerID = objP.getPlayerID(); //cursor.getInt(cursor.getColumnIndex(COLUMN_PLAYER_ID));

        //Creates level1
        ContentValues Level1Values = new ContentValues();

        Level1Values.put(COLUMN_LEVEL_DESCRIPTION, "level 1");
        Level1Values.put(COLUMN_LEVEL_UNLOCKED, "true");
        Level1Values.put(COLUMN_PLAYER_ID_L, PlayerID);

        db.insert(TABLE_LEVEL, null, Level1Values);

        //Creates level2
        ContentValues Level2Values = new ContentValues();

        Level2Values.put(COLUMN_LEVEL_DESCRIPTION, "level 2");
        Level2Values.put(COLUMN_LEVEL_UNLOCKED, "false");
        Level2Values.put(COLUMN_PLAYER_ID_L, PlayerID);

        db.insert(TABLE_LEVEL, null, Level2Values);

        //Creates level3
        ContentValues Level3Values = new ContentValues();

        Level3Values.put(COLUMN_LEVEL_DESCRIPTION, "level 3");
        Level3Values.put(COLUMN_LEVEL_UNLOCKED, "false");
        Level3Values.put(COLUMN_PLAYER_ID_L, PlayerID);

        db.insert(TABLE_LEVEL, null, Level3Values);

        db.close();
        return objP;
    }

    public void saveChanges(Player objPlayer){
        ContentValues PlayerValues = new ContentValues();

        int amountOfDobbles = objPlayer.getAmountOfDobbles();
        String currentLevel = objPlayer.getCurrentLevel();
        boolean pinguïnsUse = objPlayer.getPinguïnsUse();

        PlayerValues.put(COLUMN_AMOUNT_OF_DOBBLES,amountOfDobbles);
        PlayerValues.put(COLUMN_CURRENT_LEVEL, currentLevel);
        PlayerValues.put(COLUMN_PINGUÏNS_USE_P, String.valueOf(pinguïnsUse));

        SQLiteDatabase db = this.getWritableDatabase();

        db.update(TABLE_PLAYER, PlayerValues, COLUMN_PLAYERID + "=" + objPlayer.getPlayerID(), null);

        db.close();
    }

    public Player[] GetAllPlayers(){
        String query = "SELECT * FROM " + TABLE_PLAYER;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Player[] playerArray = new Player[cursor.getCount()];

        Player objPlayer = null;

        int s = 0;
        while(cursor.moveToNext()){

            objPlayer = new Player(cursor.getInt(cursor.getColumnIndex(COLUMN_PLAYERID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PLAYER_NAME)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT_OF_DOBBLES)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CURRENT_LEVEL)),
                    Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_PINGUÏNS_USE_P))));
            playerArray[s] = objPlayer;
            s++;
        }
        return playerArray;
    }

    public Score[] GetRanking(){
        String query = "SELECT PlayerName, Duration, Level_ID_S, NumberOfFails, ScorePoints, PinguïnsUse, AmountOfDices, Player_ID_S " +
                "FROM Score S INNER JOIN Player P " +
                "ON S.Player_ID_S = P.PlayerID " +
                "GROUP BY PlayerName " +
                "ORDER BY ScorePoints ASC, Level_ID_S DESC, Duration DESC, AmountOfDices DESC, NumberOfFails ASC";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Score[] scoreArray = new Score[cursor.getCount()];

        Score objS = null;

        int s = 0;
        while(cursor.moveToNext()){

            String st = cursor.getString(cursor.getColumnIndex(COLUMN_DURATION));
            int i = Integer.valueOf(st);
            long milisec = i  * 1000;
            Time objTime = new Time(milisec);

            objS = new Score(cursor.getString(cursor.getColumnIndex(COLUMN_PLAYER_NAME)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_POINTS)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NUMBER_OF_FAILS)),
                    objTime,
                    cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT_OF_DICES)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_LEVEL_ID_S)),
                    Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_PINGUÏNS_USE))),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PLAYER_ID_S)));
            scoreArray[s] = objS;
            s++;
        }

        return scoreArray;
    }

    public Level GetLevel(int playerID, String omschrijving){
        String query =  "SELECT * FROM " + TABLE_LEVEL + " WHERE " + COLUMN_PLAYER_ID_L
                + " = '" + playerID + "' AND " + COLUMN_LEVEL_DESCRIPTION + " = '" + omschrijving + "';" ;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Level objL = null;

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            objL = new Level(cursor.getInt(cursor.getColumnIndex(COLUMN_LEVELID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_LEVEL_DESCRIPTION)),
                    Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_LEVEL_UNLOCKED))),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PLAYER_ID_L)));
            cursor.close();
        }
        else{
            objL = null;
        }
        db.close();
        return objL;
    }

    public Score[] getPlayerScores(Player objPlayer){
        String query =  "SELECT * FROM " + TABLE_SCORE + " WHERE " + COLUMN_PLAYER_ID_S
                + " = '" + objPlayer.getPlayerID() + "';" ;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Score[] scoreArray = new Score[cursor.getCount()];

        Score objS = null;

        int s = 0;
        while(cursor.moveToNext()){

            String st = cursor.getString(cursor.getColumnIndex(COLUMN_DURATION));
            int i = Integer.valueOf(st);
            long milisec = i  * 1000;
            Time objTime = new Time(milisec);

            objS = new Score(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE_POINTS)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NUMBER_OF_FAILS)),
                    objTime,
                    cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT_OF_DICES)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_LEVEL_ID_S)),
                    Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_PINGUÏNS_USE))),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PLAYER_ID_S)));
            scoreArray[s] = objS;
            s++;
        }

        return scoreArray;
    }

    public void unlockLevel(int _playerID, String _level){
        /*
        ContentValues levelValues = new ContentValues();

        levelValues.put(COLUMN_LEVEL_UNLOCKED, "true");

        SQLiteDatabase db = this.getWritableDatabase();

        db.update(TABLE_LEVEL, levelValues, COLUMN_PLAYERID + " = '" + _playerID + "' AND " + COLUMN_LEVEL_DESCRIPTION + " = '" + _level + "'", null);

        db.close();
        */

        String query = "UPDATE " + TABLE_LEVEL
                + " SET LevelUnlocked = 'true'"
                + " WHERE Player_ID_L = " + _playerID
                + " AND LevelDescription = '" + _level + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query);

        db.close();
    }

    public void makeScore(Score objScore){
        ContentValues PlayerValues = new ContentValues();

        double scorePoints = objScore.getScorePoints();
        int scoreWrong = objScore.getScoreWrong();
        int duration = (objScore.getDuration().getMinutes() * 60) + objScore.getDuration().getSeconds();
        int amountOfDices =objScore.getAmountOfDices();
        int levelID = objScore.getLevelID();
        boolean pinguïnsUse = objScore.getIsPinguinUse();
        int playerID = objScore.getPlayerID();

        PlayerValues.put(COLUMN_SCORE_POINTS, scorePoints);
        PlayerValues.put(COLUMN_NUMBER_OF_FAILS, scoreWrong);
        PlayerValues.put(COLUMN_DURATION, String.valueOf(duration));
        PlayerValues.put(COLUMN_AMOUNT_OF_DICES, amountOfDices);
        PlayerValues.put(COLUMN_LEVEL_ID_S, levelID);
        PlayerValues.put(COLUMN_PINGUÏNS_USE, String.valueOf(pinguïnsUse));
        PlayerValues.put(COLUMN_PLAYER_ID_S, playerID);

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_SCORE, null, PlayerValues);

        db.close();
    }
}
