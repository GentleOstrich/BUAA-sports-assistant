package com.example.myapplication.ui.team;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Bean.SportsBean;
import com.example.myapplication.Bean.TeamBean;
import com.example.myapplication.Bean.UserBean;
import com.example.myapplication.Dao.TeamDao;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentTeamBinding;
import com.example.myapplication.ui.sport.MoreActivity;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment extends Fragment {

    private FragmentTeamBinding binding;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTeamBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView=root.findViewById(R.id.team_list_view);
        TeamDao teamDao = new TeamDao(this.getContext());
        List<TeamBean> teamlist = null;
        teamlist = teamDao.queryAll();
        if(teamlist==null) teamlist=new ArrayList<>();
        TeamAdapter adapter=new TeamAdapter(this.getContext(),R.layout.team_item,teamlist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TeamBean teamBean = (TeamBean) listView.getItemAtPosition(i);

                    }
                }
        );
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}