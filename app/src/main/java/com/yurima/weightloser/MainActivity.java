package com.yurima.weightloser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_weight_activity)
    Button weightActivityButton;
    @BindView(R.id.btn_food_activity)
    Button foodActivityButton;
    @BindView(R.id.btn_graph_activity)
    Button graphActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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

}
