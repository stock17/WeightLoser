package com.yurima.weightloser.food;

public class Food {
    private String title;
    private String unit;
    private String value;

    public Food(String title, String unit, String value) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
