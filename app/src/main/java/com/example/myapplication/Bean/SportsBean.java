package com.example.myapplication.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "sports")
public class SportsBean {
    @DatabaseField(generatedId = true)
    private int sportsId;
    @DatabaseField(canBeNull = false)
    private String type;
    @DatabaseField(canBeNull = false)
    private String time;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private UserBean usrId;

    private static int cnt = 0;
    public SportsBean(int sportsId, String type, String time, UserBean usrId) {
        this.sportsId = sportsId;
        this.type = type;
        this.time = time;
        this.usrId = usrId;
    }

    public SportsBean() {
        this.sportsId = cnt++;
//        this.type = type;
//        this.time = time;
//        this.usrId = usrId;
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

    public UserBean getUsrId() {
        return usrId;
    }

    public void setUsrId(UserBean usrId) {
        this.usrId = usrId;
    }
}
