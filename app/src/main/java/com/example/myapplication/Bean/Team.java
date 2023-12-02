package com.example.myapplication.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "team")
public class Team {
    @DatabaseField(generatedId = true)
    private int teamId;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Sports sportsId;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User organizerId;

    public Team(int teamId, Sports sportsId, User organizerId) {
        this.teamId = teamId;
        this.sportsId = sportsId;
        this.organizerId = organizerId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public Sports getSportsId() {
        return sportsId;
    }

    public void setSportsId(Sports sportsId) {
        this.sportsId = sportsId;
    }

    public User getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(User organizerId) {
        this.organizerId = organizerId;
    }

    // 省略构造方法和getter/setter方法
}
