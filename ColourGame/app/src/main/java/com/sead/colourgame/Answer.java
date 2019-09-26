package com.sead.colourgame;

public class Answer {
    private String values;
    private int key;
    private String color;

    public Answer(String values, int key, String color) {
        this.values = values;
        this.key = key;
        this.color = color;
    }

    public String getValues() {
        return values;
    }

    public int getKey() {
        return key;
    }

    public String getColor() {
        return color;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
