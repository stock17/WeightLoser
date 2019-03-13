package com.yurima.weightloser.database;

import android.provider.BaseColumns;

public final class DbContract {

    private DbContract(){}

    public static class FoodEntry implements BaseColumns {
        public static final String TABLE_NAME = "food";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMB_NAME_UNIT = "unit";
        public static final String COLUMB_NAME_VALUE = "value";
    }
}
