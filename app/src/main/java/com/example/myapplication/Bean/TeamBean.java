package com.example.myapplication.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "team")
public class TeamBean {
    @DatabaseField(generatedId = true)
    private int teamId;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private SportsBean sportsBeanId;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private UserBean organizerId;

    private static int cnt = 0;
    public TeamBean(int teamId, SportsBean sportsBeanId, UserBean organizerId) {
        this.teamId = teamId;
        this.sportsBeanId = sportsBeanId;
        this.organizerId = organizerId;
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

    public SportsBean getSportsId() {
        return sportsBeanId;
    }

    public void setSportsId(SportsBean sportsBeanId) {
        this.sportsBeanId = sportsBeanId;
    }

    public UserBean getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(UserBean organizerId) {
        this.organizerId = organizerId;
    }

    // 省略构造方法和getter/setter方法
}
