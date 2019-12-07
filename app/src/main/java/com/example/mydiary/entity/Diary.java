package com.example.mydiary.entity;


/*
 * @author sam
 * @date 19-12-7 下午3:22
 */

import java.util.Date;

public class Diary {
    private String title;
    private String date;
    private String content;

    public Diary(String title, String date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
