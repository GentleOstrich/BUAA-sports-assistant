package com.example.myapplication.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "team2user")
public class Team2UserBean {
    @DatabaseField(generatedId = true)
    private int id;

    // 复合主键
    @DatabaseField(foreign = true, foreignAutoRefresh = true, uniqueCombo = true)
    private TeamBean team;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, uniqueCombo = true)
    private UserBean participant;

    private static int cnt = 0;
    public Team2UserBean(TeamBean gameId, UserBean participantId) {
        this.id = cnt++;
        this.team = gameId;
        this.participant = participantId;
    }

    public Team2UserBean() {
        this.id = cnt++;
//        this.team = gameId;
//        this.participant = participantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TeamBean getTeam() {
        return team;
    }

    public void setGameId(TeamBean gameId) {
        this.team = gameId;
    }

    public UserBean getParticipant() {
        return participant;
    }

    public void setParticipant(UserBean participant) {
        this.participant = participant;
    }
}
