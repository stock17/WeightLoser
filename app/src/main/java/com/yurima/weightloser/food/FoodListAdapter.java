package com.yurima.weightloser.food;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yurima.weightloser.R;
import com.yurima.weightloser.database.DbContract;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yury on 13.03.2019.
 */

public class FoodListAdapter extends RecyclerView.Adapter <FoodListAdapter.ViewHolder> {

    //mockData
    String[] names = new String[] {"cereal", "soup", "salad"};
    String[] units = new String[] {"100gr", "100gr", "100gr"};
    String[] values = new String[] {"100cal", "200cal", "50cal"};
    //end mock data

    //Real data
    List<String> namesList = new ArrayList<>();
    List<String> unitsList = new ArrayList<>();
    List<String> valuesList = new ArrayList<>();


    public FoodListAdapter(Cursor cursor){
        //TODO get data from parameters

        cursor.moveToFirst();


        while(cursor.moveToNext()) {
            namesList.add(cursor.getString(cursor.getColumnIndex(DbContract.FoodEntry.COLUMN_NAME_TITLE)));
            unitsList.add(cursor.getString(cursor.getColumnIndex(DbContract.FoodEntry.COLUMN_NAME_UNIT)));
            valuesList.add(cursor.getString(cursor.getColumnIndex(DbContract.FoodEntry.COLUMN_NAME_VALUE)));
        }
        cursor.close();
    }

    @Override
    public FoodListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.nameTextView.setText(names[position]);
//        holder.unitTextView.setText(units[position]);
//        holder.valueTextView.setText(values[position]);
          holder.nameTextView.setText(namesList.get(position));
          holder.unitTextView.setText(unitsList.get(position));
          holder.valueTextView.setText(valuesList.get(position));


    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //TextView nameTextView;
        @BindView(R.id.tv_food_list_name) TextView nameTextView;
        @BindView(R.id.tv_food_list_unit) TextView unitTextView;
        @BindView(R.id.tv_food_list_value) TextView valueTextView;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
            //nameTextView = (TextView) view.findViewById(R.id.tv_food_list_name);
        }
    }


}
