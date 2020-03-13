package com.yurima.weightloser.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.yurima.weightloser.GraphActivity;
import com.yurima.weightloser.R;
import com.yurima.weightloser.food.FoodActivity;
import com.yurima.weightloser.main.model.MainModel;
import com.yurima.weightloser.weight.WeightActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    MainPresenter mPresenter;

    @BindView(R.id.btn_weight_activity)
    Button weightActivityButton;
    @BindView(R.id.btn_food_activity)
    Button foodActivityButton;
    @BindView(R.id.btn_graph_activity)
    Button graphActivityButton;
    @BindView(R.id.textview_expected)
    TextView expectedCaloriesTextview;
    @BindView(R.id.textview_consumed)
    TextView consumedCaloriesTextview;
    @BindView(R.id.textview_weight)
    TextView weightTextview;
    @BindView(R.id.textview_date)
    TextView dateTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this, new MainModel(this));
    }

    @OnClick({R.id.btn_graph_activity, R.id.btn_weight_activity, R.id.btn_food_activity})
    public void openActivity(Button b){
        Intent intent;
        switch (b.getId()) {
            case R.id.btn_weight_activity :
                intent = new Intent(this, WeightActivity.class);
                break;
            case R.id.btn_food_activity :
                intent = new Intent(this, FoodActivity.class);
                break;
            case R.id.btn_graph_activity :
                intent = new Intent(this, GraphActivity.class);
                break;
            default:
                return;
        }

        startActivity(intent);
    }


    @Override
    public void showExpectedCalories(String cal) {
        expectedCaloriesTextview.setText(cal);
    }

    @Override
    public void showConsumedCalories(String cal) {
        consumedCaloriesTextview.setText(cal);
    }

    @Override
    public void showWeight(String weight) {
        weightTextview.setText(weight);
    }

    @Override
    public void showDate(String date) {
        dateTextview.setText(date);
    }
}
