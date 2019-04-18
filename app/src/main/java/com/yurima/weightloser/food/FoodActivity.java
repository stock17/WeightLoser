package com.yurima.weightloser.food;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yurima.weightloser.R;
import com.yurima.weightloser.food.database.DbAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.value;
import static android.R.id.message;

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

        foodListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodListAdapter(mDbAdapter.getItems());
        foodListRecyclerView.setAdapter(adapter);
        foodListRecyclerView.addItemDecoration(new DividerItemDecoration(
                foodListRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_food_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_drop_food_table) {
            onClearTableButtonClick();
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_insert_item, null);
        final EditText titleEditText = (EditText) view.findViewById (R.id.et_insert_food_title);
        final RadioGroup unitTypeRadioGroup = (RadioGroup) view.findViewById (R.id.radiogroup_unittype);
        final EditText valueEditText = (EditText) view.findViewById (R.id.et_insert_food_value);

        builder
                .setTitle("Insert Item")
                .setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = titleEditText.getText().toString();
                        int checkedRadioButtonId = unitTypeRadioGroup.getCheckedRadioButtonId();
                        int type;
                        switch (checkedRadioButtonId) {
                            case R.id.rb_typefood_100gr:
                                type = 0;
                                break;
                            case R.id.rb_typefood_100ml:
                                type = 1;
                                break;
                            case R.id.rb_typefood_1pc:
                                type = 3;
                                break;
                            default:
                                type = 0;
                        }
                        int value = Integer.parseInt(valueEditText.getText().toString());
                        insertFood(title, type, value);
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

    private void onClearTableButtonClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.ClearAlertDialog);
        builder
                .setMessage("Warning!\n It'll destroy all the data.\n Are you sure?")
                .setPositiveButton("SURE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDbAdapter.clearTable();
                adapter.onChangeDataSet(mDbAdapter.getItems());
            }
        })

                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void insertFood(String title, int unit, int value) {
        mDbAdapter.insertItem(title, unit, value);
        adapter.onChangeDataSet(mDbAdapter.getItems());
    }

    private void removeItemFromTable(String itemTitle) {
        mDbAdapter.deleteItem(itemTitle);
        adapter.onChangeDataSet(mDbAdapter.getItems());
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
