package com.projekti.kidsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "kids.db";
    private static final String TABLE_NAME = "KIDS";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_EMAIL = "EMAIL";
    private static final String COL_PASSWORD = "PASSWORD";
    private static final String COL_BIRTHDAY = "BIRTHDAY";
    private static final String COL_GENDER = "GENDER";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    //Database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_KIDS_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " TEXT," + COL_EMAIL + " TEXT,"
                + COL_PASSWORD + " TEXT," + COL_BIRTHDAY + " TEXT," + COL_GENDER + " TEXT" + ")";
        db.execSQL(CREATE_KIDS_TABLE);
    }


    //The original table will be dropped and a new table will be created (version issues)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    //Inserting data
    long insertData(String name, String email, String password, String birthday, String gender) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("EMAIL", email);
        values.put("PASSWORD", password);
        values.put("BIRTHDAY", birthday);
        values.put("GENDER", gender);
        long result = db.insert("KIDS", null, values);
        db.close();
        return result;
    }

    //Check if the user exist
    boolean checkUser(String email, String password) {
        String[] columns = {COL_ID};

        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COL_EMAIL + " = ?" + " AND " + COL_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}

