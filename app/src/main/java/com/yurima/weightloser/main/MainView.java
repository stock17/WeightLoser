package com.yurima.weightloser.main;

import android.content.Context;

import java.util.Calendar;

public interface MainView {
    void showExpectedCalories(String cal);
    void showConsumedCalories(String cal);
    void showWeight(String weight);
    void showDate(String date);

}
