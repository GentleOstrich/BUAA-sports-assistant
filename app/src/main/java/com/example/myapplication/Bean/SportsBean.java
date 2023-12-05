package com.example.myapplication.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "sports")
public class SportsBean {
    @DatabaseField(generatedId = true)
    private int sportsId;
    @DatabaseField(canBeNull = false)
    private String type;
    @DatabaseField(canBeNull = true)
    private String dis;
    @DatabaseField(canBeNull = false)
    private String cal;
    @DatabaseField(canBeNull = false)
    private String time;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private UserBean usrBean;

    private static int cnt = 0;

    public SportsBean(String type, String dis, String cal, String time, UserBean usrBean) {
        this.sportsId = cnt++;
        this.dis = dis;
        this.cal = cal;
        this.type = type;
        this.time = time;
        this.usrBean = usrBean;
    }

    public SportsBean() {
        this.sportsId = cnt++;
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

    public UserBean getUsrBean() {
        return usrBean;
    }

    public void setUsrBean(UserBean usrBean) {
        this.usrBean = usrBean;
    }
}
