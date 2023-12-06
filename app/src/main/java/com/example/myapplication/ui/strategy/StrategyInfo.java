package com.example.myapplication.ui.strategy;

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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Bean.Team2UserBean;
import com.example.myapplication.Bean.TeamBean;
import com.example.myapplication.Bean.UserBean;
import com.example.myapplication.Dao.StrategyDao;
import com.example.myapplication.Dao.Team2UserDao;
import com.example.myapplication.Dao.TeamDao;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.R;
import com.example.myapplication.ui.team.TeamInfoActivity;
import com.example.myapplication.util.ListViewAdapter;
import com.example.myapplication.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class StrategyInfo extends AppCompatActivity {

    private TextView title;
    private TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strategy_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        title = findViewById(R.id.strategy_info_title);
        content = findViewById(R.id.strategy_info_content);
        int strategyId = getIntent().getIntExtra("data", -1);
        if (strategyId != -1) {
            title.setText(new StrategyDao(this).queryById(strategyId).getTitle());
            content.setText(new StrategyDao(this).queryById(strategyId).getContent());
        }

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

}
