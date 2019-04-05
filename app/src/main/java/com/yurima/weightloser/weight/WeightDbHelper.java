package com.yurima.weightloser.weight;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yurima.weightloser.weight.WeightDbContract.WeightEntry;



public class WeightDbHelper  extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "weightdata.db";

    private static final String SQL_CREATE_WEIGHT_TABLE =
            "CREATE TABLE " + WeightEntry.TABLE_NAME + " (" +
                    WeightEntry._ID + " INTEGER PRIMARY KEY," +
                    WeightEntry.COLUMN_NAME_DATE + " TEXT UNIQUE," +
                    WeightEntry.COLUMN_NAME_VALUE + " REAL)";

    private static final String SQL_DELETE_FOOD_TABLE =
            "DROP TABLE IF EXISTS " + WeightEntry.TABLE_NAME;


    public WeightDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_WEIGHT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_FOOD_TABLE);
        onCreate(db);
    }




}
