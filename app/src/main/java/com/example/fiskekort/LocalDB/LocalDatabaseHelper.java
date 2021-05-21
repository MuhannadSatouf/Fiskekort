package com.example.fiskekort.LocalDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LocalDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "card_local_database";
    private static final String TABLE_NAME = "card";
    private static final int DATABASE_Version = 1;

    //Columns
    private static final String CARD_ID="card_id";
    private static final String START_DATE = "start_date";
    private static final String FINISH_DATE = "finish_date";

    private static final String CREATE_TABLE ="CREATE TABLE "+TABLE_NAME+
            " ("+CARD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ START_DATE +" VARCHAR(255) ,"+ FINISH_DATE +" VARCHAR(225));";

    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
    private final Context context;

    public LocalDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e) {

        }
    }

}
