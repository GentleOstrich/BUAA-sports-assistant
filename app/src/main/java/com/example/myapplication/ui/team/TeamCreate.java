package com.example.myapplication.ui.team;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Bean.TeamBean;
import com.example.myapplication.Bean.UserBean;
import com.example.myapplication.Dao.TeamDao;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.R;

public class TeamCreate extends AppCompatActivity implements View.OnClickListener {
    private EditText teamCreateSport, teamCreateTime, teamCreateLocation;
    private Button btn_create;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_create);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标

        teamCreateSport = findViewById(R.id.team_create_sport); // 创建组局的运动类型
        teamCreateTime = findViewById(R.id.team_create_time); // 创建组局的运动时间
        teamCreateLocation = findViewById(R.id.team_create_location); // 创建组局的运动地点
        btn_create = findViewById(R.id.btn_team_create);

        teamCreateSport.addTextChangedListener(new HideTextWatcher());
        teamCreateTime.addTextChangedListener(new HideTextWatcher());
        teamCreateLocation.addTextChangedListener(new HideTextWatcher());
        btn_create.setOnClickListener(this);

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
        if (v.getId() == R.id.btn_team_create) {
            String sport = teamCreateSport.getText().toString();
            String time = teamCreateTime.getText().toString();
            String location = teamCreateLocation.getText().toString();
            String account = getSharedPreferences("user", MODE_PRIVATE).getString("account", "none");
            UserDao userDao = new UserDao(this);
            UserBean curUserBean = userDao.queryByAccount(account).get(0);
            TeamBean teamBean = new TeamBean(sport, curUserBean, time, location);
            TeamDao teamDao = new TeamDao(this);
            teamDao.insert(teamBean);
            int b = teamDao.queryAll().size();
            Toast.makeText(this, "您已创建该组局", Toast.LENGTH_SHORT).show();
        }
    }

    private class HideTextWatcher implements TextWatcher {
        public HideTextWatcher() {
            super();
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        // 在编辑框的输入文本变化后触发
        public void afterTextChanged(Editable s) {

        }
    }


}
