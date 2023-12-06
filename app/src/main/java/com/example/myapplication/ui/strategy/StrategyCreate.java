package com.example.myapplication.ui.strategy;

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

import com.example.myapplication.Bean.StrategyBean;
import com.example.myapplication.Bean.TeamBean;
import com.example.myapplication.Bean.UserBean;
import com.example.myapplication.Dao.StrategyDao;
import com.example.myapplication.Dao.TeamDao;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.R;
import com.example.myapplication.ui.team.TeamCreate;

public class StrategyCreate extends AppCompatActivity implements View.OnClickListener {
    private EditText strategyCreateTitle, strategyCreateContent;
    private Button btn_create;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strategy_create);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标

        strategyCreateTitle = findViewById(R.id.strategy_create_title); // 创建组局的运动类型
        strategyCreateContent = findViewById(R.id.strategy_create_content); // 创建组局的运动时间
        btn_create = findViewById(R.id.btn_strategy_create);

        strategyCreateTitle.addTextChangedListener(new StrategyCreate.HideTextWatcher());
        strategyCreateContent.addTextChangedListener(new StrategyCreate.HideTextWatcher());
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
        if (v.getId() == R.id.btn_strategy_create) {
            String title = strategyCreateTitle.getText().toString();
            String content = strategyCreateContent.getText().toString();
            String account = getSharedPreferences("user", MODE_PRIVATE).getString("account", "none");
            UserDao userDao = new UserDao(this);
            UserBean curUserBean = userDao.queryByAccount(account).get(0);
            StrategyBean strategyBean = new StrategyBean(title, content,null,curUserBean);
            StrategyDao strategyDao = new StrategyDao(this);
            strategyDao.insert(strategyBean);
            int b = strategyDao.queryAll().size();
            Toast.makeText(this, "您已创建该攻略", Toast.LENGTH_SHORT).show();
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
