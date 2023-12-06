package com.example.myapplication.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "team")
public class TeamBean {
    @DatabaseField(generatedId = true)
    private int teamId;
    @DatabaseField()
    private String sport;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private UserBean organizerId;
    @DatabaseField()
    private String time;
    @DatabaseField()
    private String location;

    private static int cnt = 0;
    public TeamBean(String sport, UserBean organizerId, String time, String location) {
        this.teamId = cnt++;
        this.sport = sport;
        this.organizerId = organizerId;
        this.time = time;
        this.location = location;
    }

    public TeamBean() {
        this.teamId = cnt++;
//        this.sportsBeanId = sportsBeanId;
//        this.organizerId = organizerId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public UserBean getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(UserBean organizerId) {
        this.organizerId = organizerId;
    }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    // 省略构造方法和getter/setter方法
}
