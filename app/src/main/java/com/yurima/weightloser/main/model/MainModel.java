package com.yurima.weightloser.main.model;

import android.content.Context;

import java.util.List;

public class MainModel {

    private Context mContext;
    private DayPointDataBase mDataBase;

    public MainModel(Context context) {
        mContext = context;
        mDataBase = DayPointDataBase.getInstance(context);
    }

    public List<DayPoint> getDayPointList(){
       return (mDataBase == null) ? null : mDataBase.dayPointDao().getDayPoints();
    }


}
