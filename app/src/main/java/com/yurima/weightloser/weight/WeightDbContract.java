package com.yurima.weightloser.weight;


import android.provider.BaseColumns;

public final class WeightDbContract {

    private WeightDbContract(){}

    public static class WeightEntry implements BaseColumns {
        public static final String TABLE_NAME = "weight";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME_DATE = "day";
        public static final String COLUMN_NAME_VALUE = "value";

    }
}
