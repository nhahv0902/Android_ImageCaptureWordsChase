package com.nhahv.imagecapturewordschase.models;

/**
 * Created by Nhahv on 31/5/2016.
 */
public class Question {
    private int id;
    private String name;

    public Question(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
