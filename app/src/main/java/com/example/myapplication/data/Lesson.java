package com.example.myapplication.data;

public class Lesson {
    String lessonname;
    String lessondetail;

    public Lesson(String lessonname, String lessondetail) {
        this.lessonname = lessonname;
        this.lessondetail = lessondetail;
    }

    public Lesson() {

    }

    public String getName() {
        return lessonname;
    }

    public void setName(String title) {
        this.lessonname = title;
    }

    public String getDetails() {
        return lessondetail;
    }

    public void setDetails(String details) {
        this.lessondetail = details;
    }
}
