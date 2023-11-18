package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RunActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_sport);
        findViewById(R.id.btn_run_return_home).setOnClickListener(this);
        TextView textView = findViewById(R.id.text_sport);
        if (intent.getIntExtra("run", -1) == 0) {
            textView.setText("run");
        } else if (intent.getIntExtra("basketball", -1) == 0) {
            textView.setText("basketball");
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_run_return_home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


}