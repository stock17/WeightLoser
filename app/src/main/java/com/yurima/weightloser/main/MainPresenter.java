package com.yurima.weightloser.main;

import android.graphics.Point;

import com.yurima.weightloser.main.model.DayPoint;
import com.yurima.weightloser.main.model.MainModel;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;

public class MainPresenter {

    private List<DayPoint> mData;
    private DayPoint mPoint;
    private MainView mView;
    private MainModel mModel;

    public MainPresenter(MainView view, MainModel model) {
        mView = view;
        mModel = model;

        loadData();

        // for the testing ********************
        mPoint = new DayPoint(DateAdapter.getTodayTime(), 77);
        mPoint.setConsumedCalories(1880);
        updateView();
        // *************************************
    }

    private void loadData() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mData = mModel.getDayPointList();
            }
        });
    }

    public void updateView(){
        mView.showDate(DateAdapter.TimestampToString(mPoint.getDate()));
        mView.showExpectedCalories("2000");
        mView.showConsumedCalories(String.valueOf(mPoint.getConsumedCalories()));
        mView.showWeight(String.valueOf(mPoint.getWeight()));
    }


}
