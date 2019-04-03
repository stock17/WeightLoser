package com.yurima.weightloser.food;

public class Food {
    private String title;
    private String unit;
    private int value;


    public Food(String title, String unit, int value) {
        this.title = title;
        this.unit = unit;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
