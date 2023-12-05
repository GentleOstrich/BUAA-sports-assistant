package com.example.myapplication.ui.sport;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Bean.SportsBean;
import com.example.myapplication.Bean.UserBean;
import com.example.myapplication.Dao.SportsDao;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.R;

import java.util.List;

public class BadmintonActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_cal;
    private EditText et_time;
    private Button btn_submit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_badminton);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标

        et_cal = findViewById(R.id.cal_badminton);
        et_time = findViewById(R.id.time_badminton);
        btn_submit = findViewById(R.id.btn_badminton);

        et_cal.addTextChangedListener(new HideTextWatcher());
        et_time.addTextChangedListener(new HideTextWatcher());
        btn_submit.setOnClickListener(this);

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

    public void onClick(View view) {
        if (view.getId() == R.id.btn_badminton) {
            String dis = "";
            String cal = et_cal.getText().toString();
            String time = et_time.getText().toString();
            String account = getSharedPreferences("user", MODE_PRIVATE).getString("account", "none");
            if (cal.equals("") || time.equals("")) {
                Toast.makeText(this, "请输入正确运动记录", Toast.LENGTH_SHORT).show();
            } else {
                if (!account.equals("none")) {
                    UserDao userDao = new UserDao(this);
                    List<UserBean> userBeans = userDao.queryByAccount(account);
                    for (UserBean userBean : userBeans) {
                        SportsBean sportsBean = new SportsBean("run", dis, cal, time, userBean);
                        SportsDao sportsDao = new SportsDao(this);
                        sportsDao.insert(sportsBean);
                    }
                    Toast.makeText(this, "尊敬的用户：" + account + "，您已提交一条运动记录", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
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