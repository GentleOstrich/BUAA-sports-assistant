package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Bean.SportsBean;
import com.example.myapplication.Dao.SportsDao;
import com.example.myapplication.util.ListViewAdapter;
import com.example.myapplication.util.ViewHolder;


import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private String account;
    private TextView et_account;
    private ListView history_list;
    private ListViewAdapterWithViewHolder arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_history);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标
        account = getSharedPreferences("user", MODE_PRIVATE).getString("account", "none");
        et_account = findViewById(R.id.history_account);
        et_account.setText("尊敬的用户：" + account + "，您的运动历史如下");
        history_list = findViewById(R.id.history_list);
        arrayAdapter = new ListViewAdapterWithViewHolder(this, getHistoryList());
        history_list.setAdapter(arrayAdapter);
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

    private List<SportsBean> getHistoryList() {
        SportsDao sportsDao = new SportsDao(this);
        List<SportsBean> sportsBeans = sportsDao.queryByAccount(account);
        return sportsBeans;
    }


    public class ListViewAdapterWithViewHolder extends ListViewAdapter<SportsBean> {

        //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
        public ListViewAdapterWithViewHolder(Context context, List<SportsBean> datas) {
            super(context, datas, R.layout.historylist_item);
        }

        @Override
        public void convert(ViewHolder holder, SportsBean bean) {
            ((TextView) holder.getView(R.id.sport_type)).setText(bean.getType());
            ((TextView) holder.getView(R.id.sport_time)).setText(bean.getTime());
        }
    }
}
