package com.yurima.weightloser.food;

public class Food {
    private String title;
    private int unitType;
    private int value;


    public Food(String title, int unitType, int value) {
        this.title = title;
        this.unitType = unitType;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUnit() {
        return unitType;
    }

    public String getStringUnit() {
        switch (unitType){
            case 1:
                return "100 ml";
            case 2:
                return "1 piece";
            default:
                return "100 gr";
        }
    }

    public void setUnit(int unit) {
        this.unitType = unitType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
