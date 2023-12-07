package com.example.myapplication;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Unit;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.QWeather;

import java.util.Objects;

public class RecommendActivity extends AppCompatActivity {
    private TextView weather;
    private TextView wendu;
    private TextView fengli;
    private TextView fengxiang;
    private TextView sport;

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
        queryWeather();
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
        QWeather.getWeatherNow(RecommendActivity.this, "CN101280603", Lang.ZH_HANS, Unit.METRIC, new QWeather.OnResultWeatherNowListener() {
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
                    String tianqi = now.getText();
                    String wendu1 = now.getTemp() + "℃";
                    String fengli1 = now.getWindScale();
                    String fengxiang1 = now.getWindDir();
                    weather.setText(tianqi);
                    wendu.setText(wendu1);
                    fengli.setText(fengli1);
                    fengxiang.setText(fengxiang1);
                    if (Objects.equals(tianqi, "晴")) {
                        sport.setText("户外运动，例如跑步");
                    } else {
                        sport.setText("室内运动，例如游泳");
                    }
                } else {
                    //在此查看返回数据失败的原因
                    Code code = weatherBean.getCode();
                    System.out.println("失败代码: " + code);
                    //Log.i(TAG, "failed code: " + code);
                }
            }

        });
    }

}