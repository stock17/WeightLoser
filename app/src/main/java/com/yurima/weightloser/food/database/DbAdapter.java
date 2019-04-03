package com.yurima.weightloser.food.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public long insertItem(String title, String unit, int value) {
        ContentValues values = new ContentValues();
        values.put(DbContract.FoodEntry.COLUMN_NAME_TITLE, title);
        values.put(DbContract.FoodEntry.COLUMN_NAME_UNIT, unit);
        values.put(DbContract.FoodEntry.COLUMN_NAME_VALUE, value);
        return mDatabase.insert(DbContract.FoodEntry.TABLE_NAME, null, values);
    }

    public int deleteItem(String title) {
        String whereClause = "TITLE = '" + title+ "'";
        return mDatabase.delete(DbContract.FoodEntry.TABLE_NAME, whereClause, null);
    }

    public void clearTable(){
        mDatabase.delete(DbContract.FoodEntry.TABLE_NAME, null, null);
    }

    public List<Food> getItems() {
        Cursor cursor = mDatabase.query(DbContract.FoodEntry.TABLE_NAME, null, null, null,null,null,null);
        List<Food> foodList = new ArrayList<>();
        while(cursor.moveToNext()) {
            foodList.add(new Food (
                    cursor.getString(cursor.getColumnIndex(DbContract.FoodEntry.COLUMN_NAME_TITLE)),
                    cursor.getString(cursor.getColumnIndex(DbContract.FoodEntry.COLUMN_NAME_UNIT)),
                    cursor.getInt(cursor.getColumnIndex(DbContract.FoodEntry.COLUMN_NAME_VALUE)))
            );
        }
        return foodList;
    }

    private void createMockTable() {
        insertItem("tie", "100 gr", 300);
        insertItem("coffee", "100 gr", 200);
        insertItem("bread", "100 gr", 500);
        insertItem("butter", "100 gr", 350);
        insertItem("soup", "100 gr", 237);
        insertItem("cereal", "100 gr", 138);
        insertItem("porridge", "100 gr", 111);
        insertItem("vodka", "100 gr", 1050);
    }
}
