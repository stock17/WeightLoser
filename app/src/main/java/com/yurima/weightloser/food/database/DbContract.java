package com.yurima.weightloser.food.database;

import android.provider.BaseColumns;

public final class DbContract {

    private DbContract(){}

    public static class FoodEntry implements BaseColumns {
        public static final String TABLE_NAME = "food";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_UNITTYPE = "unit";
        public static final String COLUMN_NAME_VALUE = "value";
    }
}
