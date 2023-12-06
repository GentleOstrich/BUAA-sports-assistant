package com.example.myapplication.ui.strategy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Bean.StrategyBean;
import com.example.myapplication.Bean.TeamBean;
import com.example.myapplication.R;

import java.util.List;


public class StrategyAdapter extends ArrayAdapter<StrategyBean> {
    public StrategyAdapter(@NonNull Context context, int resource, @NonNull List<StrategyBean> objects) {
        super(context, resource, objects);
    }
    //每个子项被滚动到屏幕内的时候会被调用
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        StrategyBean strategyBean=getItem(position);//得到当前项的 Team 实例
        //为每一个子项加载设定的布局
        View view= LayoutInflater.from(getContext()).inflate(R.layout.strategy_item,parent,false);
        //分别获取 image view 和 textview 的实例

        TextView strategytitle = view.findViewById(R.id.strategy_title);
        TextView strategypublisher = view.findViewById(R.id.strategy_publisher);
        // 设置要显示的图片和文字
        strategytitle.setText(strategyBean.getTitle());
        strategypublisher.setText(strategyBean.getPublisherId().getAccount());
        return view;
    }
}
