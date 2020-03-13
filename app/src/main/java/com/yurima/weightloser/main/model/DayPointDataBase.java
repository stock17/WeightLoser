package com.yurima.weightloser.main.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = DayPoint.class, exportSchema = false, version = 1)
public abstract class DayPointDataBase extends RoomDatabase {
    private static final String DB_NAME = "daypoint_db";
    private static DayPointDataBase instance;

    public static synchronized DayPointDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DayPointDataBase.class, DB_NAME).build();
        }
        return instance;
    }
    public abstract DayPointDao dayPointDao();
}
