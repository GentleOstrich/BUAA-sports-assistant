package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MenuItem item1;

    private MenuItem item2;

    private MenuItem item3;

    private MenuItem item4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_team, R.id.navigation_strategies)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        SharedPreferences sharedPreferences = getSharedPreferences("user", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("account");
        editor.apply();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //第二个参数表示此menu的id值，在onOptionsItemSelected方法中通过id值判断是哪个menu被点击了
        item1 = menu.add(Menu.NONE, 1, 1, "登录");
        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            String account = getSharedPreferences("user", MODE_PRIVATE).getString("account", "none");
            if (!account.equals("none")) {
                item1.setTitle("退出登录");
                if (menu.findItem(2) == null) {
                    item2 = menu.add(Menu.NONE, 2, 2, "发布组局");
                    item3 = menu.add(Menu.NONE, 3, 3, "发布攻略");
                    item4 = menu.add(Menu.NONE, 4, 4, "运动历史");
                }
            } else {
                item1.setTitle("登录");
                MenuItem itemToRemove = menu.findItem(2);
                if (itemToRemove != null) {
                    menu.removeItem(2);
                }
                itemToRemove = menu.findItem(3);
                if (itemToRemove != null) {
                    menu.removeItem(3);
                }
                itemToRemove = menu.findItem(4);
                if (itemToRemove != null) {
                    menu.removeItem(4);
                }

            }
        }
        return super.onMenuOpened(featureId, menu);
    }


    //点击实现的操作
    public boolean onOptionsItemSelected(MenuItem item) {
        String account = getSharedPreferences("user", MODE_PRIVATE).getString("account", "none");
        switch (item.getItemId()) {
            case 1:
                if (account.equals("none")) {
                    startActivity(new Intent(this, LoginSQLiteActivity.class));
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("user", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("account");
                    editor.apply();
                }
                break;
            case 2:

                break;
            case 3:
                break;
            case 4:
                if (!account.equals("none")) {
                    startActivity(new Intent(this, HistoryActivity.class));
                }
                break;
        }
        return true;
    }


}