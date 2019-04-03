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

    public long insertItem(String title, int unit, int value) {
        ContentValues values = new ContentValues();
        values.put(DbContract.FoodEntry.COLUMN_NAME_TITLE, title);
        values.put(DbContract.FoodEntry.COLUMN_NAME_UNITTYPE, unit);
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
        String orderBy = DbContract.FoodEntry.COLUMN_NAME_TITLE + " ASC";
        Cursor cursor = mDatabase.query(DbContract.FoodEntry.TABLE_NAME, null, null, null, null, null, orderBy);
        List<Food> foodList = new ArrayList<>();
        while(cursor.moveToNext()) {
            foodList.add(new Food (
                    cursor.getString(cursor.getColumnIndex(DbContract.FoodEntry.COLUMN_NAME_TITLE)),
                    cursor.getInt(cursor.getColumnIndex(DbContract.FoodEntry.COLUMN_NAME_UNITTYPE)),
                    cursor.getInt(cursor.getColumnIndex(DbContract.FoodEntry.COLUMN_NAME_VALUE)))
            );
        }
        return foodList;
    }

    private void createMockTable() {
        insertItem("tie", 1, 300);
        insertItem("coffee", 1, 200);
        insertItem("bread", 2, 500);
        insertItem("butter", 0, 350);
        insertItem("soup", 1, 237);
        insertItem("cereal", 0, 138);
        insertItem("porridge", 0, 111);
        insertItem("vodka", 1, 1050);
    }
}
