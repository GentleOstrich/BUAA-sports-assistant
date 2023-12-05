package com.example.myapplication.ui.team;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Bean.TeamBean;
import com.example.myapplication.R;

//用于将上下文、listview 子项布局的 id 和数据都传递过来
public class TeamAdapter extends ArrayAdapter<TeamBean> {
    public TeamAdapter(@NonNull Context context, int resource, @NonNull List<TeamBean> objects) {
        super(context, resource, objects);
    }
    //每个子项被滚动到屏幕内的时候会被调用
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TeamBean teamBean=getItem(position);//得到当前项的 Team 实例
        //为每一个子项加载设定的布局
        View view=LayoutInflater.from(getContext()).inflate(R.layout.team_item,parent,false);
        //分别获取 image view 和 textview 的实例

        TextView teamsport=view.findViewById(R.id.team_sport);
        TextView teamorganizer=view.findViewById(R.id.team_organizer);
        // 设置要显示的图片和文字
        teamsport.setText(teamBean.getSportsId().getType());
        teamorganizer.setText(teamBean.getOrganizerId().getAccount());
        return view;
    }
}