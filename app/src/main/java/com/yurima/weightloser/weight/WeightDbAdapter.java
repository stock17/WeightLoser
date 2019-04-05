package com.yurima.weightloser.weight;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yurima.weightloser.food.Food;
import com.yurima.weightloser.food.database.DbContract;
import com.yurima.weightloser.food.database.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.value;
import static com.yurima.weightloser.food.database.DbContract.FoodEntry.COLUMN_NAME_TITLE;

public class WeightDbAdapter {

    SQLiteOpenHelper mHelper;
    SQLiteDatabase mDatabase;
    Context mContext;

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    public WeightDbAdapter(Context context) {
        mContext = context;
        mHelper = new WeightDbHelper(context);
    }

    public void openDB(){
        if (mDatabase == null)
            mDatabase = mHelper.getWritableDatabase();
    }

    public void closeDB(){
        if (mDatabase != null)
            mDatabase.close();
    }

    public long insertItem(Date date, double weight) {
        ContentValues values = new ContentValues();
        values.put(WeightDbContract.WeightEntry.COLUMN_NAME_DATE, sdf.format(date));
        values.put(WeightDbContract.WeightEntry.COLUMN_NAME_VALUE, weight);
        return mDatabase.insert(WeightDbContract.WeightEntry.TABLE_NAME, null, values);
    }

    public int deleteItem(String title) {
        //TODO
        String whereClause = "TITLE = '" + title+ "'";
        return mDatabase.delete(DbContract.FoodEntry.TABLE_NAME, whereClause, null);
    }

    public void clearTable(){
        mDatabase.delete(WeightDbContract.WeightEntry.TABLE_NAME, null, null);
    }

    public Map<Date, Double> getItems()  {
        String orderBy = WeightDbContract.WeightEntry.COLUMN_NAME_DATE + " ASC";
        Cursor cursor = mDatabase.query(WeightDbContract.WeightEntry.TABLE_NAME, null, null, null, null, null, orderBy);
        Map<Date, Double> map = new HashMap<>();

        while(cursor.moveToNext()) {
            try {
                Date date = sdf.parse(cursor.getString(1));
                Double weight = cursor.getDouble(2);
                map.put(date,weight);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public void createMockTable() {
        insertItem(new Date(), 85);
        insertItem(new Date(new Date().getTime() - 1000 * 60 * 60 * 24), 84);
        insertItem(new Date(new Date().getTime() - 1000 * 60 * 60 * 24 * 2), 83);
    }
}
