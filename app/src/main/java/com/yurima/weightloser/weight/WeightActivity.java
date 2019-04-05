package com.yurima.weightloser.weight;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yurima.weightloser.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static android.R.attr.data;
import static android.R.attr.format;

public class WeightActivity extends AppCompatActivity {

    WeightDbAdapter mDbAdapter = new WeightDbAdapter(this);
    Map<Date, Double> data;

    Date date = getToday();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");


    long DAY = 1000 * 60 * 60 * 24;
    long WEEK = DAY * 7;

    int MAX_WEIGHT = 300;

    @BindView(R.id.textview_date)
    TextView dateTextView;
    @BindView(R.id.button_decrease_date)
    Button decreaseDateButton;
    @BindView(R.id.button_increase_date)
    Button increaseDateButton;
    @BindView(R.id.button_weight_ok) Button okButton;
    @BindView(R.id.editText_weight)
    EditText weightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        ButterKnife.bind(this);


        mDbAdapter.openDB();
        mDbAdapter.clearTable();
        mDbAdapter.createMockTable();
        data = mDbAdapter.getItems();

        refresh();
    }

    private void refresh() {

        dateTextView.setText(simpleDateFormat.format(date));
        if (data.containsKey(date)){
            weightEditText.setText(String.valueOf(data.get(date).toString()));
        }
        else{
            weightEditText.setText("");
        }
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

    @OnClick(R.id.button_weight_ok)
    void onOkButtonClick(){
        if (!checkData()) {
            Toast.makeText(this, "Incorrect Data", Toast.LENGTH_SHORT).show();
            return;
        }
            saveData();
    }

    private boolean checkData(){
        if (date.getTime() == 0) return false;
        String text = weightEditText.getText().toString();
        if (text.isEmpty() || Double.parseDouble(text) > MAX_WEIGHT) return false;
        return true;
    }

    private void saveData(){
        //TODO
        Toast.makeText(this, "Saving...", Toast.LENGTH_SHORT).show();

    }

    private Date getToday(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
