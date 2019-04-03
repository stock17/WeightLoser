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

import com.yurima.weightloser.database.DbAdapter;
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
    DbAdapter mDbAdapter;
    FoodListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mDbAdapter = new DbAdapter(this);
        mDbAdapter.openDB();

        ButterKnife.bind(this);

//        createMockTable();

        foodListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodListAdapter(mDbAdapter.getFoods());
        foodListRecyclerView.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_food_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_drop_food_table) {
            //TODO clear table
//            SQLiteDatabase db = mHelper.getWritableDatabase();
//            db.execSQL("DROP TABLE IF EXISTS " + DbContract.FoodEntry.TABLE_NAME);
//            db.close();
            return true;
        }

        if (item.getItemId() == R.id.item_remove_food_table) {
            onRemoveButtonClick();
            return true;
        }

        if (item.getItemId() == R.id.item_insert_food_table) {
            onInsertButtonClick();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onRemoveButtonClick() {
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
    }


    private void onInsertButtonClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_insert_item, null);
        final EditText title = (EditText) view.findViewById (R.id.et_insert_food_title);
        final EditText unit = (EditText) view.findViewById (R.id.et_insert_food_unit);
        final EditText value = (EditText) view.findViewById (R.id.et_insert_food_value);

        builder
                .setTitle("Insert Item")
                .setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String stringTitle = title.getText().toString();
                        String stringUnit = unit.getText().toString();
                        String stringValue = value.getText().toString();
                        insertFood(stringTitle, stringUnit, stringValue);
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
    }

    private void insertFood(String title, String unit, String value) {
        mDbAdapter.insertFood(title, unit, value);
        adapter.onChangeDataSet(mDbAdapter.getFoods());
    }

    private void removeItemFromTable(String itemTitle) {
        mDbAdapter.deleteFood(itemTitle);
        adapter.onChangeDataSet(mDbAdapter.getFoods());
    }

    @Override
    protected void onStart() {
        mDbAdapter.openDB();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mDbAdapter.closeDB();
        super.onStop();
    }
}
