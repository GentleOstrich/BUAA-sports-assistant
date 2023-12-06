package com.example.myapplication.Bean;

import android.net.Uri;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "strategy")
public class StrategyBean {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String title;
    @DatabaseField(canBeNull = false)
    private String content;

    @DatabaseField(canBeNull = true)
    private String image;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private UserBean publisherId;

    private static int cnt = 0;

    public StrategyBean(int id, String title, String content, String image, UserBean publisherId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.publisherId = publisherId;
    }

    public StrategyBean(String title, String content, String image, UserBean publisherId) {
        this.id = cnt++;
        this.title = title;
        this.content = content;
        this.image = image;
        this.publisherId = publisherId;
    }


    public StrategyBean() {
        this.id = cnt++;
//        this.title = title;
//        this.content = content;
//        this.publisherId = publisherId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserBean getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(UserBean publisherId) {
        this.publisherId = publisherId;
    }
}
