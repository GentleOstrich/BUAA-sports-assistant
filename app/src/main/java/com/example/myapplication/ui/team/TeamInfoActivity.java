package com.example.myapplication.ui.team;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Bean.SportsBean;
import com.example.myapplication.Bean.Team2UserBean;
import com.example.myapplication.Bean.TeamBean;
import com.example.myapplication.Bean.UserBean;
import com.example.myapplication.Dao.SportsDao;
import com.example.myapplication.Dao.Team2UserDao;
import com.example.myapplication.Dao.TeamDao;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.HistoryActivity;
import com.example.myapplication.R;
import com.example.myapplication.util.ListViewAdapter;
import com.example.myapplication.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("DefaultLocale")
public class TeamInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView team_member_list;

    private ListViewAdapterWithViewHolder arrayAdapter;

    private TextView sport_type;

    private Button btn_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        sport_type = findViewById(R.id.team_info_type);
        int teamId = getIntent().getIntExtra("data", -1);
        if (teamId != -1) {
            sport_type.setText(new TeamDao(this).queryById(teamId).getSport());
        }
        btn_join = findViewById(R.id.team_info_join_btn);
        btn_join.setOnClickListener(this);
        arrayAdapter = new ListViewAdapterWithViewHolder(this, getMemberList());
        team_member_list = findViewById(R.id.team_member_list);
        team_member_list.setAdapter(arrayAdapter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private List<UserBean> getMemberList() {
        Intent i = getIntent();
        int teamId = i.getIntExtra("data", -1);
        Team2UserDao team2UserDao = new Team2UserDao(this);
        List<Team2UserBean> team2UserBeans = team2UserDao.queryByTeamId(teamId); //temp
        List<UserBean> userBeans = new ArrayList<>();
        for (Team2UserBean team2UserBean : team2UserBeans) {
            userBeans.add(team2UserBean.getParticipant());
        }
        return userBeans;
    }

    public class ListViewAdapterWithViewHolder extends ListViewAdapter<UserBean> {

        //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
        public ListViewAdapterWithViewHolder(Context context, List<UserBean> datas) {
            super(context, datas, R.layout.team_info_member_item);
        }

        @Override
        public void convert(ViewHolder holder, UserBean bean) {
            ((TextView) holder.getView(R.id.member_account)).setText(bean.getAccount());
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.team_info_join_btn) {
            String account = getSharedPreferences("user", MODE_PRIVATE).getString("account", "none");
            if (account.equals("none")) {
                Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = getIntent();
                int teamId = i.getIntExtra("data", -1);
                TeamDao teamDao = new TeamDao(this);
                TeamBean teamBean = teamDao.queryById(teamId);
                UserDao userDao = new UserDao(this);
                UserBean userBean = userDao.queryByAccount(account).get(0);
                Team2UserDao team2UserDao = new Team2UserDao(this);
                Team2UserBean team2UserBean = new Team2UserBean(teamBean, userBean);
                team2UserDao.insert(team2UserBean);
                Toast.makeText(this, "您已成功加入该组局", Toast.LENGTH_SHORT).show();
            }
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