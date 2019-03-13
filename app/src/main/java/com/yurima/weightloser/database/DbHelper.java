package com.yurima.weightloser.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.yurima.weightloser.database.DbContract.FoodEntry;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Data.db";

    private static final String SQL_CREATE_FOOD_TABLE =
            "CREATE TABLE " + FoodEntry.TABLE_NAME + " (" +
                    FoodEntry._ID + " INTEGER PRIMARY KEY," +
                    FoodEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FoodEntry.COLUMN_NAME_UNIT + " TEXT," +
                    FoodEntry.COLUMN_NAME_VALUE + " TEXT)";

    private static final String SQL_DELETE_FOOD_TABLE =
            "DROP TABLE IF EXISTS " + FoodEntry.TABLE_NAME;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_FOOD_TABLE);
        onCreate(db);
    }
}
