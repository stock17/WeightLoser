package com.yurima.weightloser.food;

public class Food {
    private String title;
    private int unitType;
    private int value;


    public Food(String title, int value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

   public int getValue() {
        return value;
    }
   public void setValue(int value) {
        this.value = value;
    }
}
