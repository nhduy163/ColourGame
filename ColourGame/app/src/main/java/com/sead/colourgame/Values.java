package com.sead.colourgame;

public class Values {

    private String values;
    private int key;

    public Values(String values, int key) {
        this.values = values;
        this.key = key;
    }

    public String getValues() {
        return values;
    }

    public int getKey() {
        return key;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
