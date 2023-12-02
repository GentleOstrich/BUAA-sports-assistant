package com.example.myapplication.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "strategy")
public class Strategy {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String title;
    @DatabaseField(canBeNull = false)
    private String content;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User publisherId;

    public Strategy(int id, String title, String content, User publisherId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publisherId = publisherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(User publisherId) {
        this.publisherId = publisherId;
    }
}
