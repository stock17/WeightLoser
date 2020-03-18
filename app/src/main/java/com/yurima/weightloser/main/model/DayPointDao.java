package com.yurima.weightloser.main.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DayPointDao {

    @Query("SELECT * from daypoint_db")
    List<DayPoint> getDayPoints();

    @Insert
    void insertDayPoint (DayPoint point);

    @Update
    void updateDayPoint (DayPoint point);

    @Delete
    void deleteDayPoint (DayPoint point);
}
