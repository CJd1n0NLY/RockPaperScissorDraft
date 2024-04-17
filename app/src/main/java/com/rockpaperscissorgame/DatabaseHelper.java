package com.rockpaperscissorgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbRPS.db";
    private static final String TABLE_NAME = "rpsHistory";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MATCHRESULT = "matchResult";

    private static final String COLUMN_PLAYER = "playerChoice";
    private static final String COLUMN_COMPUTER = "computerChoice";
    private static final String COLUMN_DATETIME = "matchDateAndTime";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COMPUTER + " TEXT, " + COLUMN_MATCHRESULT + " TEXT)");

    SQLiteDatabase database = this.getWritableDatabase();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);

    }

    public boolean addToHistory(String playerChoice, String computerChoice, String matchResult){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYER, playerChoice);
        values.put(COLUMN_COMPUTER, computerChoice);
        values.put(COLUMN_MATCHRESULT, matchResult);

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        String dateTime = dateFormat.format(new Date());
//        values.put(COLUMN_DATETIME, dateTime);

       long result = db.insert(TABLE_NAME, null,values);

       if(result == 1){
           return false;
       } else {
           return true;
       }
    }

}
