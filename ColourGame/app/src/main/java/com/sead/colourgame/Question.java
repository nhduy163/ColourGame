package com.sead.colourgame;

public class Question {
    private String color;
    private int key;

    public Question(String color, int key) {
        this.color = color;
        this.key = key;
    }

    public String getColor() {
        return color;
    }

    public int getKey() {
        return key;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
