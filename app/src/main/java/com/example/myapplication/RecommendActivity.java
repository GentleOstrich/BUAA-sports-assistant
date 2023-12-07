package com.example.myapplication;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Bean.SportsBean;
import com.example.myapplication.Dao.SportsDao;
import com.google.gson.Gson;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Unit;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.QWeather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RecommendActivity extends AppCompatActivity {
    private TextView weather;
    private TextView wendu;
    private TextView fengli;
    private TextView fengxiang;
    private TextView sport;
    private HashSet<String> indoorSports = new HashSet<>();

    private HashSet<String> outdoorSports = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        weather = findViewById(R.id.recommend_weather);
        wendu = findViewById(R.id.recommend_wendu);
        fengli = findViewById(R.id.recommend_fengli);
        fengxiang = findViewById(R.id.recommend_fengxiang);
        sport = findViewById(R.id.recommend_sport);
        initSet();
        queryWeather();
        sort();
    }

    private void initSet() {
        indoorSports.add("羽毛球");
        indoorSports.add("健身");
        indoorSports.add("乒乓球");
        indoorSports.add("游泳");
        indoorSports.add("台球");
        indoorSports.add("排球");

        outdoorSports.add("飞盘");
        outdoorSports.add("篮球");
        outdoorSports.add("足球");
        outdoorSports.add("网球");
        outdoorSports.add("跑步");
        outdoorSports.add("td");
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

    public void queryWeather() {
        QWeather.getWeatherNow(RecommendActivity.this, "CN101010100", Lang.ZH_HANS, Unit.METRIC, new QWeather.OnResultWeatherNowListener() {
            public static final String TAG = "he_feng_now";

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ", e);
                System.out.println("Weather Now Error:" + new Gson());
            }

            @Override


            public void onSuccess(WeatherNowBean weatherBean) {
                //Log.i(TAG, "getWeather onSuccess: " + new Gson().toJson(weatherBean));
                System.out.println("获取天气成功： " + new Gson().toJson(weatherBean));
                //先判断返回的status是否正确，当status正确时获取数据，若status不正确，可查看status对应的Code值找到原因
                if (Code.OK == weatherBean.getCode()) {
                    WeatherNowBean.NowBaseBean now = weatherBean.getNow();
                    String tianqi1 = now.getText();
                    String wendu1 = now.getTemp() + "℃";
                    String fengli1 = now.getWindScale();
                    String fengxiang1 = now.getWindDir();
                    weather.setText(tianqi1);
                    wendu.setText(wendu1);
                    fengli.setText(fengli1);
                    fengxiang.setText(fengxiang1);
                } else {
                    //在此查看返回数据失败的原因
                    Code code = weatherBean.getCode();
                    System.out.println("失败代码: " + code);
                    //Log.i(TAG, "failed code: " + code);
                }
            }
        });
    }


    private void sort() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String tianqi = weather.getText().toString();
        String account = getSharedPreferences("user", MODE_PRIVATE).getString("account", "none");

        if (!account.equals("none")) {
            Toast.makeText(this, "查询到您的活动历史记录", Toast.LENGTH_SHORT).show();
            SportsDao sportsDao = new SportsDao(this);
            List<SportsBean> sportsBeans = sportsDao.queryByAccount(account);
            HashMap<String, Integer> sportsCnt = new HashMap<>();
            // 记录该用户不同运动类型的次数
            for (SportsBean sportsBean : sportsBeans) {
                String type = sportsBean.getType();
                if (sportsCnt.containsKey(type)) {
                    sportsCnt.put(type, sportsCnt.get(type) + 1);
                } else {
                    sportsCnt.put(type, 1);
                }
            }
            List<Map.Entry<String, Integer>> entryList = new ArrayList<>(sportsCnt.entrySet());
            // 排序
            Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                    return entry2.getValue().compareTo(entry1.getValue());
                }
            });
            StringBuilder tmp = new StringBuilder();
            int cnt = 0;
            if (Objects.equals(tianqi, "晴")) {
                for (Map.Entry<String, Integer> entry : entryList) {
                    if (outdoorSports.contains(entry.getKey())) {
                        if (cnt == 0) {
                            tmp.append(", ");
                        }
                        cnt++;
                        tmp.append(entry.getKey());
                        if (cnt >= 3) {
                            break;
                        }
                    }
                }
                if (tmp.length() != 0) {
                    tmp.append("等");
                }
                tmp.append("户外运动");
            } else {
                for (Map.Entry<String, Integer> entry : entryList) {
                    if (indoorSports.contains(entry.getKey())) {
                        if (cnt != 0) {
                            tmp.append(", ");
                        }
                        cnt++;
                        tmp.append(entry.getKey());
                        if (cnt >= 3) {
                            break;
                        }
                    }
                }
                if (tmp.length() != 0) {
                    tmp.append("等");
                }
                tmp.append("室内运动");

            }
            sport.setText(tmp.toString());
        } else {
            Toast.makeText(this, "未查询到您的活动历史记录", Toast.LENGTH_SHORT).show();
            if (Objects.equals(tianqi, "晴")) {
                sport.setText("户外运动，例如跑步");
            } else {
                sport.setText("室内运动，例如游泳");
            }
        }

    }

}