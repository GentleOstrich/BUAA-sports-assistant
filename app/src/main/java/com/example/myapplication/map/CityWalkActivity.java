package com.example.myapplication.map;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Bean.SportsBean;
import com.example.myapplication.Bean.UserBean;
import com.example.myapplication.Dao.SportsDao;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.R;
import com.example.myapplication.ui.sport.BadmintonActivity;
import com.example.myapplication.ui.sport.RunActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.j256.ormlite.stmt.query.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CityWalkActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener{
    private ArrayList<CheckBox> checkBoxes;
    private ArrayList<RelativeLayout> checkBoxBacks;
    private HashMap<Integer, Integer> map = new HashMap<>();
//    private int[] ids = {R.id.checkbox_1, R.id.checkbox_2, R.id.checkbox_3, R.id.checkbox_4, R.id.checkbox_5, R.id.checkbox_6, R.id.checkbox_7, R.id.checkbox_8, R.id.checkbox_9, R.id.checkbox_10};
    private int[] ids = {R.id.checkbox_0, R.id.checkbox_1, R.id.checkbox_2, R.id.checkbox_3, R.id.checkbox_4, R.id.checkbox_5, R.id.checkbox_6, R.id.checkbox_7, R.id.checkbox_8, R.id.checkbox_9, R.id.checkbox_10, R.id.checkbox_11, R.id.checkbox_12, R.id.checkbox_13, R.id.checkbox_14, R.id.checkbox_15, R.id.checkbox_16, R.id.checkbox_17, R.id.checkbox_18, R.id.checkbox_19, R.id.checkbox_20, R.id.checkbox_21, R.id.checkbox_22, R.id.checkbox_23, R.id.checkbox_24, R.id.checkbox_25, R.id.checkbox_26};
    private int[] bids = {R.id.checkbox_back_0, R.id.checkbox_back_1, R.id.checkbox_back_2, R.id.checkbox_back_3, R.id.checkbox_back_4, R.id.checkbox_back_5, R.id.checkbox_back_6, R.id.checkbox_back_7, R.id.checkbox_back_8, R.id.checkbox_back_9, R.id.checkbox_back_10, R.id.checkbox_back_11, R.id.checkbox_back_12, R.id.checkbox_back_13, R.id.checkbox_back_14, R.id.checkbox_back_15, R.id.checkbox_back_16, R.id.checkbox_back_17, R.id.checkbox_back_18, R.id.checkbox_back_19, R.id.checkbox_back_20, R.id.checkbox_back_21, R.id.checkbox_back_22, R.id.checkbox_back_23, R.id.checkbox_back_24, R.id.checkbox_back_25, R.id.checkbox_back_26};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_walk);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标

//        ImageButton imageButton = root.findViewById(R.id.btn_city_walk);
//        imageButton.setBackgroundColor(Color.RED);
        int cnt = 0;
        for (int id : ids) {
            CheckBox checkBox = findViewById(id);
            checkBox.setOnCheckedChangeListener(this);
            checkBox.setChecked(false);
//            checkBoxes.add(checkBox);
            map.put(id, cnt++);
        }
//        checkBox.setOnClickListener(this);
        Button button = findViewById(R.id.btn_commit);
        button.setOnClickListener(this);
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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int id = compoundButton.getId();
        int idx = map.get(id);
        int bid = bids[idx];
        if (compoundButton.isChecked()) {
            RelativeLayout relativeLayout = findViewById(bid);
            relativeLayout.setBackgroundColor(Color.parseColor("#00CED1"));
        } else {
            RelativeLayout relativeLayout = findViewById(bid);
            relativeLayout.setBackgroundColor(Color.parseColor("#f5f5f5"));
        }
//        String desc = String.format("您%s这个checkBox",b ? "勾选":"取消勾选");
//        compoundButton.setText(desc);
    }

    private int check(int i) {
        if (i >= 0 && i <= 4) {
            return 0;
        } else if (i >= 0 && i <= 7) {
            return 1;
        } else if (i >= 8 && i <= 15) {
            return 2;
        } else if (i >= 16 && i <= 26) {
            return 3;
        }
        return -1;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_commit) {
            Intent intent = new Intent(this, BaseMapActivity.class);
            int i = -1;
            int idx = 0;
            for (int id : ids) {
                CheckBox checkBox = findViewById(id);
                BaseMapActivity.ables.add((checkBox.isChecked() ? true : false));
                if (checkBox.isChecked()) {
                    BaseMapActivity.mini = (BaseMapActivity.mini < idx) ? BaseMapActivity.mini : idx;
                    BaseMapActivity.maxi = (BaseMapActivity.maxi > idx) ? BaseMapActivity.maxi : idx;
                }
                if (i == -1) {
                    if (checkBox.isChecked()) {
                        i = check(idx);
                    }
                } else if (i == 0) {
                    if (checkBox.isChecked()) {
                        if (check(idx) == 1) {
                            i = check(idx);
                        } else if (i != check(idx)) {
                            Toast.makeText(this, "地点相距过远，无适合的walk路线！", Toast.LENGTH_SHORT).show();
                            i = -1;
                            break;
                        }
                    }
                } else {
                    if (checkBox.isChecked() && i != check(idx)) {
                        Toast.makeText(this, "地点相距过远，无适合的walk路线！", Toast.LENGTH_SHORT).show();
                        i = -1;
                        break;
                    }
                }
                idx++;
            }
            if (i != -1) {
                BaseMapActivity.iii = i;
                if (BaseMapActivity.mini >= 9) {
                    BaseMapActivity.mini += 3;
                }
                if (BaseMapActivity.maxi >= 9) {
                    BaseMapActivity.maxi += 3;
                }
//                BaseMapActivity.reNewMarkers();
                startActivity(intent);
            }
        }
//        if (view.getId() == R.id.btn_badminton) {
//            String dis = "";
//            String cal = et_cal.getText().toString();
//            String time = et_time.getText().toString();
//            String account = getSharedPreferences("user", MODE_PRIVATE).getString("account", "none");
//            if (cal.equals("") || time.equals("")) {
//                Toast.makeText(this, "请输入正确运动记录", Toast.LENGTH_SHORT).show();
//            } else {
//                if (!account.equals("none")) {
//                    UserDao userDao = new UserDao(this);
//                    List<UserBean> userBeans = userDao.queryByAccount(account);
//                    for (UserBean userBean : userBeans) {
//                        SportsBean sportsBean = new SportsBean("羽毛球", dis, cal, time, userBean);
//                        SportsDao sportsDao = new SportsDao(this);
//                        sportsDao.insert(sportsBean);
//                    }
//                    Toast.makeText(this, "尊敬的用户：" + account + "，您已提交一条运动记录", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
    }
}
