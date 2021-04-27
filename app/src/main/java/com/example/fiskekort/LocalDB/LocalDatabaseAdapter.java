package com.example.fiskekort.LocalDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class LocalDatabaseAdapter {
    private LocalDatabaseHelper localDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;


    public LocalDatabaseAdapter(Context context) {
        localDatabaseHelper = new LocalDatabaseHelper(context);
    }


    public long insertData(String start_date, String finish_date) {
        SQLiteDatabase dbb = localDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalDatabaseHelper.START_DATE, start_date);
        contentValues.put(localDatabaseHelper.FINISH_DATE, finish_date);
        long id = dbb.insert(localDatabaseHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = localDatabaseHelper.getWritableDatabase();
        String[] columns = {localDatabaseHelper.CARD_ID, localDatabaseHelper.START_DATE, localDatabaseHelper.FINISH_DATE};
        Cursor cursor = db.query(localDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int card_id = cursor.getInt(cursor.getColumnIndex(localDatabaseHelper.CARD_ID));
            String start_date = cursor.getString(cursor.getColumnIndex(localDatabaseHelper.START_DATE));
            String finish_date = cursor.getString(cursor.getColumnIndex(localDatabaseHelper.FINISH_DATE));
            buffer.append(card_id + "   " + start_date + "   " + finish_date + " \n");
        }
        return buffer.toString();
    }

    public int delete(String uname) {
        SQLiteDatabase db = localDatabaseHelper.getWritableDatabase();
        String[] whereArgs = {uname};

        int count = db.delete(localDatabaseHelper.TABLE_NAME, localDatabaseHelper.START_DATE + " = ?", whereArgs);
        return count;
    }


    public void deleteAllRows() {
        SQLiteDatabase db = localDatabaseHelper.getWritableDatabase();
        db.execSQL("delete from "+ localDatabaseHelper.TABLE_NAME);

    }

    public int updateDate(String old_start_date, String new_start_date) {
        SQLiteDatabase db = localDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(localDatabaseHelper.START_DATE, new_start_date);
        String[] whereArgs = {old_start_date};
        int count = db.update(localDatabaseHelper.TABLE_NAME, contentValues, localDatabaseHelper.START_DATE + " = ?", whereArgs);
        return count;
    }


    public class LocalDatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "card_local_database";
        private static final String TABLE_NAME = "card";
        private static final int DATABASE_Version = 1;

        //Columns
        private static final String CARD_ID = "card_id";
        private static final String START_DATE = "start_date";
        private static final String FINISH_DATE = "finish_date";

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + CARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + START_DATE + " VARCHAR(255) ," + FINISH_DATE + " VARCHAR(225));";

        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public LocalDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                create_toast(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                create_toast(context, "" + e);
            }
        }

    }


    public void create_toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}