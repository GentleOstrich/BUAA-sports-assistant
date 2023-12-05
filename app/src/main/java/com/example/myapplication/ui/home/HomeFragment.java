package com.example.myapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.ui.sport.BadmintonActivity;
import com.example.myapplication.ui.sport.BasketballActivity;
import com.example.myapplication.ui.sport.FootballActivity;
import com.example.myapplication.ui.sport.FrisbeeActivity;
import com.example.myapplication.ui.sport.GymActivity;
import com.example.myapplication.ui.sport.MoreActivity;
import com.example.myapplication.ui.sport.RunActivity;
import com.example.myapplication.ui.sport.SwimActivity;
import com.example.myapplication.ui.sport.TableTennisActivity;
import com.example.myapplication.ui.sport.TdActivity;
import com.example.myapplication.ui.sport.TennisActivity;
import com.example.myapplication.ui.sport.VolleyballActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;  // 文本的样子 指向fragment_home.xml
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        root.findViewById(R.id.btn_run).setOnClickListener(this);
        root.findViewById(R.id.btn_basketball).setOnClickListener(this);
        root.findViewById(R.id.btn_football).setOnClickListener(this);
        root.findViewById(R.id.btn_volleyball).setOnClickListener(this);
        root.findViewById(R.id.btn_badminton).setOnClickListener(this);
        root.findViewById(R.id.btn_swim).setOnClickListener(this);
        root.findViewById(R.id.btn_gym).setOnClickListener(this);
        root.findViewById(R.id.btn_tabletennis).setOnClickListener(this);
        root.findViewById(R.id.btn_tennis).setOnClickListener(this);
        root.findViewById(R.id.btn_frisbee).setOnClickListener(this);
        root.findViewById(R.id.btn_td).setOnClickListener(this);
        root.findViewById(R.id.btn_more).setOnClickListener(this);


        return root;
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        if (view.getId() == R.id.btn_run) {
            intent = new Intent(this.getContext(), RunActivity.class);
        } else if (view.getId() == R.id.btn_basketball) {
            intent = new Intent(this.getContext(), BasketballActivity.class);
        } else if (view.getId() == R.id.btn_football) {
            intent = new Intent(this.getContext(), FootballActivity.class);
        } else if (view.getId() == R.id.btn_volleyball) {
            intent = new Intent(this.getContext(), VolleyballActivity.class);
        } else if (view.getId() == R.id.btn_badminton) {
            intent = new Intent(this.getContext(), BadmintonActivity.class);
        } else if (view.getId() == R.id.btn_swim) {
            intent = new Intent(this.getContext(), SwimActivity.class);
        } else if (view.getId() == R.id.btn_gym) {
            intent = new Intent(this.getContext(), GymActivity.class);
        } else if (view.getId() == R.id.btn_tabletennis) {
            intent = new Intent(this.getContext(), TableTennisActivity.class);
        } else if (view.getId() == R.id.btn_tennis) {
            intent = new Intent(this.getContext(), TennisActivity.class);
        } else if (view.getId() == R.id.btn_frisbee) {
            intent = new Intent(this.getContext(), FrisbeeActivity.class);
        } else if (view.getId() == R.id.btn_td) {
            intent = new Intent(this.getContext(), TdActivity.class);
        } else if (view.getId() == R.id.btn_more) {
            intent = new Intent(this.getContext(), MoreActivity.class);
        }
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}