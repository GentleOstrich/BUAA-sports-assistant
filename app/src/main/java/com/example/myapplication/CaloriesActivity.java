package com.example.myapplication;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.j256.ormlite.stmt.query.In;

public class CaloriesActivity  extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6,spinner7,spinner8,spinner9,spinner10;
    EditText caloriesnum;
    TextView result;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        spinner5 = findViewById(R.id.spinner5);
        spinner6 = findViewById(R.id.spinner6);
        spinner7 = findViewById(R.id.spinner7);
        spinner8 = findViewById(R.id.spinner8);
        spinner9 = findViewById(R.id.spinner9);
        spinner10 = findViewById(R.id.spinner10);
        caloriesnum = findViewById(R.id.calories_num);
        findViewById(R.id.btn_cal).setOnClickListener(this);
        result = findViewById(R.id.calories_result);
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
    public void onClick(View v) {
        String numstr = caloriesnum.getText().toString();
        int num = 1;
        if(!numstr.isEmpty()) num = Integer.parseInt(numstr);
        int i = (int)spinner1.getSelectedItemId();
        if(i!=0) {
            String[] food1 = getResources().getStringArray(R.array.food1);
            int[] calories1 = getResources().getIntArray(R.array.calories1);
            result.setText(food1[i]+" "+num+"份："+calories1[i]*num+"Calories");
            return;
        }
        i = (int)spinner2.getSelectedItemId();
        if(i!=0) {
            String[] food2 = getResources().getStringArray(R.array.food2);
            int[] calories2 = getResources().getIntArray(R.array.calories2);
            result.setText(food2[i]+" "+num+"份："+calories2[i]*num+"Calories");
            return;
        }
        i = (int)spinner3.getSelectedItemId();
        if(i!=0) {
            String[] food3 = getResources().getStringArray(R.array.food3);
            int[] calories3 = getResources().getIntArray(R.array.calories3);
            result.setText(food3[i]+" "+num+"份："+calories3[i]*num+"Calories");
            return;
        }
        i = (int)spinner4.getSelectedItemId();
        if(i!=0) {
            String[] food4 = getResources().getStringArray(R.array.food4);
            int[] calories4 = getResources().getIntArray(R.array.calories4);
            result.setText(food4[i]+" "+num+"份："+calories4[i]*num+"Calories");
            return;
        }
        i = (int)spinner5.getSelectedItemId();
        if(i!=0) {
            String[] food5 = getResources().getStringArray(R.array.food5);
            int[] calories5 = getResources().getIntArray(R.array.calories5);
            result.setText(food5[i]+" "+num+"份："+calories5[i]*num+"Calories");
            return;
        }
        i = (int)spinner6.getSelectedItemId();
        if(i!=0) {
            String[] food6 = getResources().getStringArray(R.array.food6);
            int[] calories6 = getResources().getIntArray(R.array.calories6);
            result.setText(food6[i]+" "+num+"份："+calories6[i]*num+"Calories");
            return;
        }
        i = (int)spinner7.getSelectedItemId();
        if(i!=0) {
            String[] food7 = getResources().getStringArray(R.array.food7);
            int[] calories7 = getResources().getIntArray(R.array.calories7);
            result.setText(food7[i]+" "+num+"份："+calories7[i]*num+"Calories");
            return;
        }
        i = (int)spinner8.getSelectedItemId();
        if(i!=0) {
            String[] food8 = getResources().getStringArray(R.array.food8);
            int[] calories8 = getResources().getIntArray(R.array.calories8);
            result.setText(food8[i]+" "+num+"份："+calories8[i]*num+"Calories");
            return;
        }
        i = (int)spinner9.getSelectedItemId();
        if(i!=0) {
            String[] food9 = getResources().getStringArray(R.array.food9);
            int[] calories9 = getResources().getIntArray(R.array.calories9);
            result.setText(food9[i]+" "+num+"份："+calories9[i]*num+"Calories");
            return;
        }
        i = (int)spinner10.getSelectedItemId();
        if(i!=0) {
            String[] food10 = getResources().getStringArray(R.array.food10);
            int[] calories10 = getResources().getIntArray(R.array.calories10);
            result.setText(food10[i]+" "+num+"份："+calories10[i]*num+"Calories");
            return;
        }
    }
}
