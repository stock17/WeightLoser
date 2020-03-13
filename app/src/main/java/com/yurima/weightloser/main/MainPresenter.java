package com.yurima.weightloser.main;

import com.yurima.weightloser.main.model.DayPoint;
import com.yurima.weightloser.main.model.MainModel;

import java.util.List;

public class MainPresenter {

    List<DayPoint> mData;
    MainView mView;
    MainModel mModel;

    public MainPresenter(MainView view, MainModel model) {
        mView = view;
        mModel = model;
    }


}
