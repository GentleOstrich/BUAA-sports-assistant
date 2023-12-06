package com.example.myapplication.ui.strategy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Bean.StrategyBean;
import com.example.myapplication.Bean.TeamBean;
import com.example.myapplication.Dao.StrategyDao;
import com.example.myapplication.Dao.TeamDao;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentStrategyBinding;
import com.example.myapplication.ui.team.TeamAdapter;
import com.example.myapplication.ui.team.TeamInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class StrategyFragment extends Fragment implements AdapterView.OnItemClickListener {

    private FragmentStrategyBinding binding;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentStrategyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView=root.findViewById(R.id.strategy_list_view);
        StrategyDao strategyDao = new StrategyDao(this.getContext());
        List<StrategyBean> strategylist = null;
        strategylist = strategyDao.queryAll();
        if(strategylist==null) strategylist=new ArrayList<>();
        StrategyAdapter adapter=new StrategyAdapter(this.getContext(),R.layout.strategy_item,strategylist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this::onItemClick);

        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        StrategyBean strategyBean = (StrategyBean) listView.getItemAtPosition(position);
        Intent intent = new Intent(this.getContext(), StrategyInfo.class);
        intent.putExtra("data", strategyBean.getId());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}