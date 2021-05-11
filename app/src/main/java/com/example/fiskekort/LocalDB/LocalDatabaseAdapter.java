package com.example.fiskekort.LocalDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.fiskekort.FishingCard;

import java.util.ArrayList;

public class LocalDatabaseAdapter {
    private final LocalDatabaseHelper localDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;


    public LocalDatabaseAdapter(Context context) {
        localDatabaseHelper = new LocalDatabaseHelper(context);
    }


    public long insertData(String start_date, String finish_date) {
        SQLiteDatabase dbb = localDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalDatabaseHelper.START_DATE, start_date);
        contentValues.put(LocalDatabaseHelper.FINISH_DATE, finish_date);
        long id = dbb.insert(LocalDatabaseHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = localDatabaseHelper.getWritableDatabase();
        String[] columns = {LocalDatabaseHelper.CARD_ID, LocalDatabaseHelper.START_DATE, LocalDatabaseHelper.FINISH_DATE};
        Cursor cursor = db.query(LocalDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int card_id = cursor.getInt(cursor.getColumnIndex(LocalDatabaseHelper.CARD_ID));
            String start_date = cursor.getString(cursor.getColumnIndex(LocalDatabaseHelper.START_DATE));
            String finish_date = cursor.getString(cursor.getColumnIndex(LocalDatabaseHelper.FINISH_DATE));
            buffer.append(card_id + "   " + start_date + "   " + finish_date + " \n");
        }
        return buffer.toString();
    }

    public ArrayList<FishingCard> getListOfData(){
        ArrayList<FishingCard> cardList = new ArrayList<>();

        sqLiteDatabase = localDatabaseHelper.getReadableDatabase();
        String[] field = {LocalDatabaseHelper.CARD_ID, LocalDatabaseHelper.START_DATE, LocalDatabaseHelper.FINISH_DATE};
        Cursor c = sqLiteDatabase.query(LocalDatabaseHelper.TABLE_NAME, field, null, null, null, null, null);

        int id = c.getColumnIndex(LocalDatabaseHelper.CARD_ID);
        int startDate = c.getColumnIndex(LocalDatabaseHelper.START_DATE);
        int finishDate = c.getColumnIndex(LocalDatabaseHelper.FINISH_DATE);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            String cardId = c.getString(id);
            String startDateString = c.getString(startDate);
            String finishDateString = c.getString(finishDate);
            cardList.add(new FishingCard(cardId, startDateString, finishDateString));

        }

        return cardList;
    }



    public int delete(String uname) {
        SQLiteDatabase db = localDatabaseHelper.getWritableDatabase();
        String[] whereArgs = {uname};

        int count = db.delete(LocalDatabaseHelper.TABLE_NAME, LocalDatabaseHelper.START_DATE + " = ?", whereArgs);
        return count;
    }


    public void deleteAllRows() {
        SQLiteDatabase db = localDatabaseHelper.getWritableDatabase();
        db.execSQL("delete from "+ LocalDatabaseHelper.TABLE_NAME);

    }

    public int updateDate(String old_start_date, String new_start_date) {
        SQLiteDatabase db = localDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalDatabaseHelper.START_DATE, new_start_date);
        String[] whereArgs = {old_start_date};
        int count = db.update(LocalDatabaseHelper.TABLE_NAME, contentValues, LocalDatabaseHelper.START_DATE + " = ?", whereArgs);
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
        private final Context context;

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
