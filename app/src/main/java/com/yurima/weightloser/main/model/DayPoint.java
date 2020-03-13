package com.yurima.weightloser.main.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.time.LocalDate;


@Entity
public class DayPoint {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private LocalDate date;
    @ColumnInfo
    private double weight;
    @ColumnInfo
    private double consumedCalories;

    public DayPoint (int id, LocalDate date, double weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    @Ignore
    public DayPoint (LocalDate date, double weight) {
        this.date = date;
        this.weight = weight;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
}
