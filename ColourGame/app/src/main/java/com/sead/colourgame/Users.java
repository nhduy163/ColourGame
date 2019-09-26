package com.sead.colourgame;

import java.sql.Timestamp;

public class Users {
    private int rank;
    private String name;
    private int score;
    private String date;

    public Users() {
    }

    public Users(String name, int score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public Users(int rank, String name, int score, String date) {
        this.rank = rank;
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
