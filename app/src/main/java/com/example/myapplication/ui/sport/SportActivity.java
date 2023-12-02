package com.example.myapplication.ui.sport;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

public class SportActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_sport);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标
        TextView textView = findViewById(R.id.text_sport);
        if (intent.getIntExtra("run", -1) == 0) {
            textView.setText("run");
        } else if (intent.getIntExtra("basketball", -1) == 0) {
            textView.setText("basketball");
        } else if (intent.getIntExtra("football",-1) == 0){
            textView.setText("football");
        } else if (intent.getIntExtra("volleyball",-1) == 0){
            textView.setText("volleyball");
        } else if (intent.getIntExtra("badminton",-1) == 0){
            textView.setText("badminton");
        } else if (intent.getIntExtra("swim",-1) == 0){
            textView.setText("swim");
        } else if (intent.getIntExtra("gym",-1) == 0){
            textView.setText("gym");
        } else if (intent.getIntExtra("tabletennis",-1) == 0){
            textView.setText("tabletennis");
        } else if (intent.getIntExtra("tennis",-1) == 0){
            textView.setText("tennis");
        } else if (intent.getIntExtra("frisbee",-1) == 0){
            textView.setText("frisbee");
        } else if (intent.getIntExtra("td",-1) == 0){
            textView.setText("td");
        } else if (intent.getIntExtra("more",-1) == 0){
            textView.setText("more");
        }
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
    public void onClick(View view) {

    }


}