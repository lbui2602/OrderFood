package com.example.orderfood.uis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.orderfood.R;
import com.example.orderfood.models.Food;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.FoodRepository;

import java.util.List;

public class GetAllActivity extends AppCompatActivity {

    DBHelper dbHelper;
    List<Food> foodList;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all);

        dbHelper = new DBHelper(this);
        foodList = new FoodRepository(dbHelper).getAll();

        recyclerView = findViewById(R.id.rcl_view);

        GetAllRecyclerViewAdapter adapter = new GetAllRecyclerViewAdapter(this, foodList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}