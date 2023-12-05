package com.example.myapplication.ui.sport;

import android.app.AlertDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Bean.SportsBean;
import com.example.myapplication.Bean.UserBean;
import com.example.myapplication.Dao.SportsDao;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.R;
import com.example.myapplication.util.ViewUtil;

import java.util.List;
import com.example.myapplication.database.MdbHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RunActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_dis;
    private EditText et_cal;
    private EditText et_time;
    private Button btn_submit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_run);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标

        et_dis = findViewById(R.id.dis_run);
        et_cal = findViewById(R.id.cal_run);
        et_time = findViewById(R.id.time_run);
        btn_submit = findViewById(R.id.btn_run);

        et_dis.addTextChangedListener(new HideTextWatcher(et_dis, 10));
        et_cal.addTextChangedListener(new HideTextWatcher(et_cal, 10));
        et_time.addTextChangedListener(new HideTextWatcher(et_time, 10));
        btn_submit.setOnClickListener(this);

        mdbHelper = MdbHelper.getInstance(this); // 获得用户数据库帮助器的实例

//        findViewById(R.id.btn_commit).setOnClickListener(this);
        initChronometer();
    }

    private void initChronometer() {
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_reset).setOnClickListener(this);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        btn_reset = findViewById(R.id.btn_reset);
        chronometer = findViewById(R.id.chronometer);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
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

    private MdbHelper mdbHelper;
    private Button btn_start, btn_stop, btn_reset, btn_format_1;
    private Chronometer chronometer, ch_format;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_run) {
            String dis = et_dis.getText().toString();
            String cal = et_cal.getText().toString();
            String time = et_time.getText().toString();
            String account = getSharedPreferences("user", MODE_PRIVATE).getString("account", "none");
            if (dis.equals("") || cal.equals("") || time.equals("")) {
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
        } else if (view.getId() == R.id.btn_start)  {
            //SystemClock.elapsedRealtime(),自启动以来经过的毫秒数。
            //设置基准时间
            long v = SystemClock.elapsedRealtime();
            chronometer.setBase(SystemClock.elapsedRealtime());
            //true,倒计时
            chronometer.setCountDown(false);
            chronometer.setFormat("%s");
            //开始计时
            chronometer.start();
        } else if (view.getId() == R.id.btn_stop)  {
            //结束计时
            chronometer.stop();
        } else if (view.getId() == R.id.btn_reset)  {
            //重置基准时间
            chronometer.setBase(SystemClock.elapsedRealtime());
        }
    }

    // 定义一个编辑框监听器，在输入文本达到指定长度时自动隐藏输入法
    private class HideTextWatcher implements TextWatcher {
        private EditText mView; // 声明一个编辑框对象
        private int mMaxLength; // 声明一个最大长度变量

        public HideTextWatcher(EditText v, int maxLength) {
            super();
            mView = v;
            mMaxLength = maxLength;
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        // 在编辑框的输入文本变化后触发
        public void afterTextChanged(Editable s) {
            String str = s.toString(); // 获得已输入的文本字符串
            // 输入文本达到11位（如手机号码），或者达到6位（如登录密码）时关闭输入法
            if ((str.length() == 11 && mMaxLength == 11)
                    || (str.length() == 6 && mMaxLength == 6)) {
                ViewUtil.hideOneInputMethod(RunActivity.this, mView); // 隐藏输入法软键盘
            }
        }
    }

}