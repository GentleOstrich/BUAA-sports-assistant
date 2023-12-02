package com.example.myapplication.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "sports")
public class Sports {
    @DatabaseField(generatedId = true)
    private int sportsId;
    @DatabaseField(canBeNull = false)
    private String type;
    @DatabaseField(canBeNull = false)
    private String time;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User usrId;

    public Sports(int sportsId, String type, String time, User usrId) {
        this.sportsId = sportsId;
        this.type = type;
        this.time = time;
        this.usrId = usrId;
    }

    public int getSportsId() {
        return sportsId;
    }

    public void setSportsId(int sportsId) {
        this.sportsId = sportsId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUsrId() {
        return usrId;
    }

    public void setUsrId(User usrId) {
        this.usrId = usrId;
    }
}
