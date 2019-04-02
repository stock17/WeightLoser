package com.yurima.weightloser;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.yurima.weightloser.database.DbContract;
import com.yurima.weightloser.database.DbHelper;
import com.yurima.weightloser.food.FoodListAdapter;

import java.sql.RowId;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class FoodActivity extends AppCompatActivity {

    @BindView(R.id.rv_food_list)
    RecyclerView foodListRecyclerView;

    SQLiteOpenHelper mHelper = new DbHelper(this);

    FoodListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ButterKnife.bind(this);

//        createMockTable();


        Cursor cursor = readTable();

        foodListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodListAdapter(cursor);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_drop_food_table) {
            SQLiteDatabase db = mHelper.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + DbContract.FoodEntry.TABLE_NAME);
            db.close();
            return true;
        }

        if (item.getItemId() == R.id.item_remove_food_table) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.dialog_remove_item, null);
            final EditText et = (EditText) view.findViewById (R.id.et_remove_food_table);

            builder
                    .setTitle("Remove Item")
                    .setView(view)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String removeItem = et.getText().toString();
                            removeItemFromTable(removeItem);
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
            ;

            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Cursor readTable(){
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DbContract.FoodEntry.TABLE_NAME, null, null, null,null,null,null);
        return cursor;
    }

    private void removeItemFromTable(String itemTitle) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String whereClause = "TITLE = '" + itemTitle+ "'";
        db.delete(DbContract.FoodEntry.TABLE_NAME, whereClause, null);
        Cursor cursor = readTable();
        adapter.onChangeDataSet(cursor);

    }

    private void createMockTable() {
        insertEntry("tie", "100 gr", "300 cal");
        insertEntry("coffee", "100 gr", "200 cal");
        insertEntry("bread", "100 gr", "500 cal");
        insertEntry("butter", "100 gr", "350 cal");
        insertEntry("soup", "100 gr", "237 cal");
        insertEntry("cereal", "100 gr", "138 cal");
        insertEntry("porridge", "100 gr", "111 cal");
        insertEntry("vodka", "100 gr", "1050 cal");
    }
}
