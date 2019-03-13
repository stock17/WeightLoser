package com.yurima.weightloser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yurima.weightloser.food.FoodListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodActivity extends AppCompatActivity {

    @BindView(R.id.rv_food_list)
    RecyclerView foodListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ButterKnife.bind(this);

        foodListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FoodListAdapter adapter = new FoodListAdapter();
        foodListRecyclerView.setAdapter(adapter);
    }
}
