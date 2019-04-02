package com.yurima.weightloser.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.yurima.weightloser.food.Food;

import java.util.ArrayList;
import java.util.List;


public class DBAdapter {
    SQLiteOpenHelper mHelper;
    SQLiteDatabase mDatabase;
    Context mContext;

    public DBAdapter(Context context) {
        mContext = context;
    }

    public void openDB(){
        mDatabase = mHelper.getWritableDatabase();
    }

    public void closeDB(){
        mDatabase.close();
    }

    public long insertFood (String title, String unit, String value) {
        ContentValues values = new ContentValues();
        values.put(DbContract.FoodEntry.COLUMN_NAME_TITLE, title);
        values.put(DbContract.FoodEntry.COLUMN_NAME_UNIT, unit);
        values.put(DbContract.FoodEntry.COLUMN_NAME_VALUE, value);
        return mDatabase.insert(DbContract.FoodEntry.TABLE_NAME, null, values);
    }

    public int deleteFood (String title) {
        String whereClause = "TITLE = '" + title+ "'";
        return mDatabase.delete(DbContract.FoodEntry.TABLE_NAME, whereClause, null);
    }

    public List<Food> getFoods() {
        Cursor cursor = mDatabase.query(DbContract.FoodEntry.TABLE_NAME, null, null, null,null,null,null);
        List<Food> foodList = new ArrayList<>();
        while(cursor.moveToNext()) {
            foodList.add(new Food (
                    cursor.getString(cursor.getColumnIndex(DbContract.FoodEntry.COLUMN_NAME_TITLE)),
                    cursor.getString(cursor.getColumnIndex(DbContract.FoodEntry.COLUMN_NAME_UNIT)),
                    cursor.getString(cursor.getColumnIndex(DbContract.FoodEntry.COLUMN_NAME_VALUE)))
            );
        }
        return foodList;
    }
}
