package com.yurima.weightloser.main.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.yurima.weightloser.main.DateAdapter;

import java.time.LocalDate;
import java.util.Date;


@Entity
public class DayPoint {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    @TypeConverters({DateAdapter.class})
    private Date date;
    @ColumnInfo
    private double weight;
    @ColumnInfo
    private double consumedCalories;

    public DayPoint (int id, Date date, double weight, double consumedCalories) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.consumedCalories = consumedCalories;
    }

    @Ignore
    public DayPoint (Date date, double weight) {
        this.date = date;
        this.weight = weight;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getConsumedCalories() {
        return consumedCalories;
    }

    public void setConsumedCalories(double consumedCalories) {
        this.consumedCalories = consumedCalories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
