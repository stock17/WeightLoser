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


public class DbAdapter {
    SQLiteOpenHelper mHelper;
    SQLiteDatabase mDatabase;
    Context mContext;

    public DbAdapter(Context context) {
        mContext = context;
        mHelper = new DbHelper(context);
    }

    public void openDB(){
        if (mDatabase == null)
            mDatabase = mHelper.getWritableDatabase();
    }

    public void closeDB(){
        if (mDatabase != null)
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

    private void createMockTable() {
        insertFood("tie", "100 gr", "300 cal");
        insertFood("coffee", "100 gr", "200 cal");
        insertFood("bread", "100 gr", "500 cal");
        insertFood("butter", "100 gr", "350 cal");
        insertFood("soup", "100 gr", "237 cal");
        insertFood("cereal", "100 gr", "138 cal");
        insertFood("porridge", "100 gr", "111 cal");
        insertFood("vodka", "100 gr", "1050 cal");
    }
}
