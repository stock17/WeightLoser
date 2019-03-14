package com.yurima.weightloser;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.yurima.weightloser.database.DbContract;
import com.yurima.weightloser.database.DbHelper;
import com.yurima.weightloser.food.FoodListAdapter;

import java.sql.RowId;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodActivity extends AppCompatActivity {

    @BindView(R.id.rv_food_list)
    RecyclerView foodListRecyclerView;

    SQLiteOpenHelper mHelper = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ButterKnife.bind(this);

        // create table
//        insertEntry("tie", "100 gr", "300 cal");
//        insertEntry("coffee", "100 gr", "200 cal");
//        insertEntry("cacao", "100 gr", "50 cal");

        Cursor cursor = readTable();

        foodListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FoodListAdapter adapter = new FoodListAdapter(cursor);
        foodListRecyclerView.setAdapter(adapter);
    }

    private void insertEntry(String title, String unit, String value) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.FoodEntry.COLUMN_NAME_TITLE, title);
        values.put(DbContract.FoodEntry.COLUMN_NAME_UNIT, unit);
        values.put(DbContract.FoodEntry.COLUMN_NAME_VALUE, value);
        long newRowId = db.insert(DbContract.FoodEntry.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_food_activity, menu);
        return true;
    }

    private Cursor readTable(){
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DbContract.FoodEntry.TABLE_NAME, null, null, null,null,null,null);
        return cursor;
    }
}
