package com.example.myapplication.ui.team;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Bean.TeamBean;
import com.example.myapplication.Bean.UserBean;
import com.example.myapplication.Dao.TeamDao;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.R;

public class TeamCreate extends AppCompatActivity implements View.OnClickListener {
    private TextView teamCreateSport,teamCreateTime,teamCreateLocation;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_create);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标

        teamCreateSport = findViewById(R.id.team_create_sport); // 创建组局的运动类型
        teamCreateTime = findViewById(R.id.team_create_time); // 创建组局的运动时间
        teamCreateLocation = findViewById(R.id.team_create_location); // 创建组局的运动地点
        findViewById(R.id.btn_team_create).setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View v) {
        String sport = teamCreateSport.getText().toString();
        String time = teamCreateTime.getText().toString();
        String location = teamCreateLocation.getText().toString();
        String account = getSharedPreferences("user", MODE_PRIVATE).getString("account", "none");
        UserDao userDao = new UserDao(this);
        UserBean curUserBean = userDao.queryByAccount(account).get(0);
        TeamBean teamBean = new TeamBean(sport,curUserBean,time,location);
        TeamDao teamDao = new TeamDao(this);
        teamDao.insert(teamBean);
    }
}
