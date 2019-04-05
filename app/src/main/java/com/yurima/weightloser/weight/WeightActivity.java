package com.yurima.weightloser.weight;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.yurima.weightloser.R;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class WeightActivity extends AppCompatActivity {

    Date date = new Date();

    long DAY = 1000 * 60 * 60 * 24;
    long WEEK = DAY * 7;

    @BindView(R.id.textview_date)
    TextView dateTextView;
    @BindView(R.id.button_decrease_date)
    Button decreaseDateButton;
    @BindView(R.id.button_increase_date)
    Button increaseDateButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        ButterKnife.bind(this);
        refresh();
    }

    private void refresh() {

        DateFormat format = DateFormat.getDateInstance(DateFormat.DATE_FIELD);
        String stringDate = format.format(date);
        dateTextView.setText(stringDate);
    }

    @OnClick(R.id.button_decrease_date)
    void onDecreaseButtonClick(){
        date.setTime(date.getTime() - DAY);
        refresh();
    }

    @OnClick(R.id.button_increase_date)
    void onIncreaseButtonClick(){
        date.setTime(date.getTime() + DAY);
        refresh();
    }

    @OnLongClick(R.id.button_decrease_date)
    boolean onDecreaseButtonLongClick(){
        date.setTime(date.getTime() - WEEK);
        refresh();
        return true;
    }

    @OnLongClick(R.id.button_increase_date)
    boolean onIncreaseButtonLongClick(){
        date.setTime(date.getTime() + WEEK);
        refresh();
        return true;
    }
}
