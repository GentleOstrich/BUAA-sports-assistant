package com.example.myapplication.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "team2user")
public class Team2UserBean {
    @DatabaseField
    private int id;

    // 复合主键
    @DatabaseField(foreign = true, foreignAutoRefresh = true, uniqueCombo = true)
    private TeamBean gameId;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, uniqueCombo = true)
    private UserBean participantId;

    public Team2UserBean(int id, TeamBean gameId, UserBean participantId) {
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

    public TeamBean getGameId() {
        return gameId;
    }

    public void setGameId(TeamBean gameId) {
        this.gameId = gameId;
    }

    public UserBean getParticipantId() {
        return participantId;
    }

    public void setParticipantId(UserBean participantId) {
        this.participantId = participantId;
    }
}
