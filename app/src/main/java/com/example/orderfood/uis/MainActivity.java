package com.example.orderfood.uis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.example.orderfood.R;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.FoodRepository;
import com.example.orderfood.models.database.UserRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnvMain;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    public void initView() {
        bnvMain=findViewById(R.id.bnvMain);
        NavHostFragment navHostFragment= (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController=navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bnvMain,navController);
//        deleteDatabase("food.db");
        dbHelper=new DBHelper(this);
        UserRepository userRepository=new UserRepository(dbHelper);
//        Log.d("TAG", "initView: "+userRepository.getAllUser().get(0).getUsername());
    }
}