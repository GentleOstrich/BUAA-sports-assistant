package com.example.myapplication.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String account;
    @DatabaseField(canBeNull = false)
    private String password;

    private static int cnt = 0;
    public User(int id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }

    public User() {
        this.id = cnt++;
        this.account = null;
        this.password = null;
    }

    public User(String account, String password) {
        this.id = cnt++;
        this.account = account;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    // 演示数据库升级时要先把下面这行注释
    // @DatabaseField
    // private String phone;

//    @Override
//    public String toString() {
//        return "OneTableBean{" +
//                "messageId=" + messageId +
//                ", batchNo='" + batchNo + '\'' +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}

