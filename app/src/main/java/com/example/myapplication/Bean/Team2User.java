package com.example.myapplication.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "team2user")
public class Team2User {
    @DatabaseField
    private int id;

    // 复合主键
    @DatabaseField(foreign = true, foreignAutoRefresh = true, uniqueCombo = true)
    private Team gameId;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, uniqueCombo = true)
    private User participantId;

    public Team2User(int id, Team gameId, User participantId) {
        this.id = id;
        this.gameId = gameId;
        this.participantId = participantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getGameId() {
        return gameId;
    }

    public void setGameId(Team gameId) {
        this.gameId = gameId;
    }

    public User getParticipantId() {
        return participantId;
    }

    public void setParticipantId(User participantId) {
        this.participantId = participantId;
    }
}
